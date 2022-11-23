package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.springboot.movies.entities.Movie;

public interface IMovieService {

	public Movie save(Movie movie);

	public List<Movie> findAll();

	public Page<Movie> findAll(Pageable pageable);

	public Movie findById(Integer id);

	public void delete(Integer id);

}
