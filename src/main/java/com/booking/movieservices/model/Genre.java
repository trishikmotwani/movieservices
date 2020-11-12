package com.booking.movieservices.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "genre")
public class Genre implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8479548333735651995L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long genre_id;
    
    private String name;

    @JsonIgnore
    @ManyToOne(targetEntity=Movie.class, fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id")
    private Movie movie;
    
    
    public Long getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	

}
