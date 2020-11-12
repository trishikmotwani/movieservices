package com.booking.movieservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booking.movieservices.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
//    Movie findByName(String name);
}