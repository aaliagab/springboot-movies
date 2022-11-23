package com.app.springboot.movies.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.springboot.movies.entities.Comment;

public interface ICommentService {
	public Comment save(Comment comment);

	public List<Comment> findAll();

	public Page<Comment> findAll(Pageable pageable);

	public Comment findById(Integer id);

	public void delete(Integer id);
}
