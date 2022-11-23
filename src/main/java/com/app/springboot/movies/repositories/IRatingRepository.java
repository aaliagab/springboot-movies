package com.app.springboot.movies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.springboot.movies.entities.Rating;

@Repository
public interface IRatingRepository extends JpaRepository<Rating, Integer>{

}
