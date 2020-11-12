package com.booking.movieservices.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6804440607388231954L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movie_id;
    
    @NotNull
    private String name;
    
    private String director;
    private double popularity;
    private double imdb_score;

    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,mappedBy = "movie",orphanRemoval = true)
    private Set<Genre> genre = new HashSet<Genre>();

    
    
	public Long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(Long movie_id) {
		this.movie_id = movie_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public double getPopularity() {
		return popularity;
	}

	public void setPopularity(double popularity) {
		this.popularity = popularity;
	}

	public double getImdb_score() {
		return imdb_score;
	}

	public void setImdb_score(double imdb_score) {
		this.imdb_score = imdb_score;
	}

	public Set<Genre> getGenre() {
		return genre;
	}

	public void setGenre(Set<Genre> genre) {
		this.genre = genre;
	}


	

}
