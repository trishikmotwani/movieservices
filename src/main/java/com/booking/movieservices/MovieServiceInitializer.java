package com.booking.movieservices;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.booking.movieservices.model.Genre;
import com.booking.movieservices.model.Movie;
import com.booking.movieservices.model.User;
import com.booking.movieservices.repository.GenreRepository;
import com.booking.movieservices.repository.MovieRepository;
import com.booking.movieservices.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MovieServiceInitializer implements CommandLineRunner {

	@Autowired
	private final MovieRepository movieRepository;

	
	@Autowired
	private final GenreRepository genreRepository;
	
	@Autowired
	private final UserRepository userRepository;
	
	MovieServiceInitializer(MovieRepository movieRepository,GenreRepository genreRepository,UserRepository userRepository) {
		this.movieRepository = movieRepository;
		this.genreRepository = genreRepository;
		this.userRepository  = userRepository;
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		JSONParser parser = new JSONParser();

	    try {
	    	
	    	// setup initial data into movies table
	    	Object obj = parser.parse(new FileReader("src/main/resources/imdb.json"));

	        JSONArray jsonObjects =  (JSONArray) obj;

	        for (Object movieObject : jsonObjects) {
	        	
	            JSONObject jsonObject = (JSONObject) movieObject;
	            
	            Movie mObj = new Movie();
	            String name = (String) jsonObject.get("name");
	            mObj.setName(name);

	            Double imdb_score = (Double)jsonObject.get("imdb_score");
	            mObj.setImdb_score(imdb_score);
	            
	            
	            Double popularity = (Double)jsonObject.get("popularity");
	            mObj.setPopularity(popularity);
	            

	            String director = (String) jsonObject.get("director");
	            mObj.setDirector(director);
	            
	            movieRepository.save(mObj);

	            JSONArray genreList = (JSONArray) jsonObject.get("genre");
	            for (Object genre: genreList) {
	            	 
	            	 String genre_name = (String) genre;
	            	 Genre genreToAdd = new Genre();
	            	 genreToAdd.setName(genre_name);
	            	 mObj.getGenre().add(genreToAdd);
	            	 genreToAdd.setMovie(mObj);
	            	 genreRepository.save(genreToAdd);
	            	 
	            }
	            
	            System.out.println(mObj.toString());
	        }
	        
	        // setup initial admin user and 10 other dummy users
	        for(int i=0;i<10;i++) {
	        	if(i==0) {
	        		User adminuser = new User();
	    	        adminuser.setName("admin");
	    	        adminuser.setEmail("admin@movies.com");
	    	        adminuser.setPassword("admin");
	    	        adminuser.setAdmin(true);
	    	        userRepository.save(adminuser);
	        	} else {
	        		User user = new User();
	        		user.setName("user" + i);
	        		user.setEmail("user" + i +"@movies.com");
	        		user.setPassword("user" + i);
	        		user.setAdmin(false);
	        		userRepository.save(user);
	        	}
	        }
	        
	        System.out.println("admin@movies.com ----" + userRepository.findByEmailPwd("admin@movies.com","test"));
	        System.out.println("user1@movies.com ------" + userRepository.findByEmailPwd("admin1@movies.com","test"));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }	
	}
	
}
