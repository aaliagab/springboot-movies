package com.app.springboot.movies.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.springboot.movies.entities.Reaction;
import com.app.springboot.movies.services.IReactionService;

@RestController
@RequestMapping("/api/reactions")
public class ReactionRestController {
	@Autowired
	private IReactionService reactionService;

	@GetMapping("/")
	public ResponseEntity<?> index() {
		Map<String, Object> response = new HashMap<>();
		List<Reaction> reactions = null;
		try {
			reactions = reactionService.findAll();
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (reactions == null || reactions.size() == 0) {
			response.put("message", "There are no reactions registered in the database");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reactions, HttpStatus.OK);
	}

	@GetMapping("/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Map<String, Object> response = new HashMap<>();
		Page<Reaction> reactions = null;
		try {
			reactions = reactionService.findAll(PageRequest.of(page - 1, 5));
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
		}
		if (reactions == null || reactions.getContent().size() == 0) {
			response.put("message", "There are no reactions registered in the database " + (page - 1));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reactions, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<?> showById(@PathVariable Integer id) {
		Reaction reaction = null;
		Map<String, Object> response = new HashMap<>();
		try {
			reaction = reactionService.findById(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (reaction == null) {
			response.put("message", "The reaction ID: ".concat(id.toString()).concat("does not exist in the database!!"));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reaction, HttpStatus.OK);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Reaction reaction) {
		Reaction new_reaction = null;
		Map<String, Object> response = new HashMap<>();
		try {
			new_reaction = reactionService.save(reaction);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (new_reaction == null) {
			response.put("message", "It was not possible to create the new reaction contact the administrator");
			new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		response.put("message", "Reaction created successfully!!");
		response.put("reaction", new_reaction);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Reaction reaction, @PathVariable Integer id) {
		Reaction reaction_actual = reactionService.findById(id), reaction_updated = null;
		Map<String, Object> response = new HashMap<>();
		if (reaction_actual == null) {
			response.put("message", "The reaction ID: ".concat(id.toString()).concat("does not exist in the database!!"));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			reaction_actual.setReaction_type(reaction.getReaction_type());
			reaction_updated = reactionService.save(reaction_actual);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error updating the reaction in the database!!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Reaction updated successfully!!");
		response.put("reaction", reaction_updated);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Reaction reaction = reactionService.findById(id);
			reactionService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database!!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Reaction successfully removed!!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
