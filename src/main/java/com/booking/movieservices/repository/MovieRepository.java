package com.booking.movieservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.movieservices.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
//    Movie findByName(String name);
}