package com.app.springboot.movies.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.springboot.movies.entities.Movie;
import com.app.springboot.movies.services.IMovieService;

@RestController
@RequestMapping("/api/movies")
public class MovieRestController {

	@Autowired
	private IMovieService movieService;
	
	@GetMapping("/")
	public ResponseEntity<?> index(){
		Map<String, Object> response = new HashMap<>();
		List<Movie> movies = null;
		try {
			movies = movieService.findAll();
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("message", "Error when querying the database");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(movies == null || movies.size()==0) {
			response.put("message", "There are no movies registered in the database");
			return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(movies, HttpStatus.OK);
	}
}
