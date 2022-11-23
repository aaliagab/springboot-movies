package com.app.springboot.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.springboot.movies.entities.Comment;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Integer>{

}
