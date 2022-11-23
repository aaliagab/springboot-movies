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

import com.app.springboot.movies.entities.Movie;
import com.app.springboot.movies.services.IMovieService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

	@Autowired
	private IMovieService movieService;

	@Operation(summary = "Get a movies list")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found movies"),
			  @ApiResponse(responseCode = "404", description = "Movies not found") })
	@GetMapping("/")
	public ResponseEntity<?> index() {
		Map<String, Object> response = new HashMap<>();
		List<Movie> movies = null;
		try {
			movies = movieService.findAll();
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (movies == null || movies.size() == 0) {
			response.put("message", "There are no movies registered in the database");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}

	@Operation(summary = "Get a movies list by page number")
	@GetMapping("/page/{page}")
	public ResponseEntity<?> index(@PathVariable Integer page) {
		Map<String, Object> response = new HashMap<>();
		Page<Movie> movies = null;
		try {
			movies = movieService.findAll(PageRequest.of(page - 1, 5));
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMessage()));
		}
		if (movies == null || movies.getContent().size() == 0) {
			response.put("message", "There are no movies registered in the database " + (page - 1));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(movies, HttpStatus.OK);

	}

	@Operation(summary = "Get a movie by Id")
	@GetMapping("/{id}")
	public ResponseEntity<?> showById(@PathVariable Integer id) {
		Movie movie = null;
		Map<String, Object> response = new HashMap<>();
		try {
			movie = movieService.findById(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (movie == null) {
			response.put("message", "The movie ID: ".concat(id.toString()).concat("does not exist in the database!!"));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(movie, HttpStatus.OK);
	}

	@Operation(summary = "Create a movie")
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Movie movie) {
		Movie new_movie = null;
		Map<String, Object> response = new HashMap<>();
		try {
			new_movie = movieService.save(movie);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (new_movie == null) {
			response.put("message", "It was not possible to create the new movie contact the administrator");
			new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		response.put("message", "Movie created successfully!!");
		response.put("movie", new_movie);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Update a movie by Id")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Movie movie, @PathVariable Integer id) {
		Movie movie_actual = movieService.findById(id), movie_updated = null;
		Map<String, Object> response = new HashMap<>();
		if (movie_actual == null) {
			response.put("message", "The movie ID: ".concat(id.toString()).concat("does not exist in the database!!"));
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		try {
			movie_actual.setTitle(movie.getTitle());
			movie_actual.setDuration_time(movie.getDuration_time());
			movie_actual.setRating(movie.getRating());
			movie_updated = movieService.save(movie_actual);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error updating the movie in the database!!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Movie updated successfully!!");
		response.put("movie", movie_updated);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Operation(summary = "Delete a movie by Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Movie movie = movieService.findById(id);
			movieService.delete(id);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database!!");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Movie successfully removed!!");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
