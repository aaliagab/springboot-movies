package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springboot.movies.entities.Rating;
import com.app.springboot.movies.repositories.IRatingRepository;

@Service
public class RatingService implements IRatingService {

	@Autowired
	private IRatingRepository ratingRepository;

	@Override
	@Transactional
	public Rating save(Rating rating) {
		// TODO Auto-generated method stub
		return ratingRepository.save(rating);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Rating> findAll() {
		// TODO Auto-generated method stub
		return ratingRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Rating> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return ratingRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Rating findById(Integer id) {
		// TODO Auto-generated method stub
		return ratingRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		ratingRepository.deleteById(id);
	}

}
