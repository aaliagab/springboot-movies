package com.app.springboot.movies.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reaction")
public class Reaction implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "reaction_type", nullable = false)
	private String reaction_type;

	@NotNull(message = "You must assign a movie")
	@JsonIgnoreProperties(value = { "comments", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;

	public Reaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReaction_type() {
		return reaction_type;
	}

	public void setReaction_type(String reaction_type) {
		this.reaction_type = reaction_type;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -6823339310101878087L;

}
