package com.app.springboot.movies.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "title", nullable = false, unique = true)
	private String title;

	@Column(name = "duration_time", nullable = false)
	private Integer duration_time;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
	private List<Reaction> reactions;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "movie", cascade = CascadeType.ALL)
	private Rating rating;

	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDuration_time() {
		return duration_time;
	}

	public void setDuration_time(Integer duration_time) {
		this.duration_time = duration_time;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 321465028189360793L;

}
