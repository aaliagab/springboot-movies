package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.springboot.movies.entities.Comment;
import com.app.springboot.movies.repositories.ICommentRepository;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private ICommentRepository commentRepository;

	@Override
	@Transactional
	public Comment save(Comment comment) {
		// TODO Auto-generated method stub
		return commentRepository.save(comment);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> findAll() {
		// TODO Auto-generated method stub
		return commentRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Comment> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return commentRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Comment findById(Integer id) {
		// TODO Auto-generated method stub
		return commentRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		commentRepository.deleteById(id);
	}

}
