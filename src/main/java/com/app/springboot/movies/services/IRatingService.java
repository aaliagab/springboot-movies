package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.springboot.movies.entities.Rating;

public interface IRatingService {
	public Rating save(Rating rating);

	public List<Rating> findAll();

	public Page<Rating> findAll(Pageable pageable);

	public Rating findById(Integer id);

	public void delete(Integer id);
}
