package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springboot.movies.entities.Movie;
import com.app.springboot.movies.repositories.IMovieRepository;

@Service
public class MovieService implements IMovieService {

	@Autowired
	private IMovieRepository movieRepository;
	
	@Override
	@Transactional
	public Movie save(Movie movie) {
		return movieRepository.save(movie);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findAll() {
		return movieRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Movie> findAll(Pageable pageable) {
		return movieRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Movie findById(Integer id) {
		return movieRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		movieRepository.deleteById(id);
	}

}
