package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springboot.movies.entities.Reaction;
import com.app.springboot.movies.repositories.IReactionRepository;

@Service
public class ReactionService implements IReactionService {

	@Autowired
	private IReactionRepository reactionRepository;

	@Override
	@Transactional
	public Reaction save(Reaction reaction) {
		// TODO Auto-generated method stub
		return reactionRepository.save(reaction);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reaction> findAll() {
		// TODO Auto-generated method stub
		return reactionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Reaction> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return reactionRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Reaction findById(Integer id) {
		// TODO Auto-generated method stub
		return reactionRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		reactionRepository.deleteById(id);
	}

}
