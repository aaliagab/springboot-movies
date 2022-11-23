package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.springboot.movies.entities.Reaction;

public interface IReactionService {
	public Reaction save(Reaction reaction);

	public List<Reaction> findAll();

	public Page<Reaction> findAll(Pageable pageable);

	public Reaction findById(Integer id);

	public void delete(Integer id);
}
