package com.booking.movieservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.booking.movieservices.repository.MovieRepository;

@SpringBootApplication
public class MovieservicesApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(MovieservicesApplication.class, args);
	}

}
