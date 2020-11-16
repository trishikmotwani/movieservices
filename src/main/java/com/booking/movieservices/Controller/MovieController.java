package com.booking.movieservices.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.booking.movieservices.model.Genre;
import com.booking.movieservices.model.Movie;
import com.booking.movieservices.model.User;
import com.booking.movieservices.repository.GenreRepository;
import com.booking.movieservices.repository.MovieRepository;
import com.booking.movieservices.repository.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.*;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/movies")
class GroupController {

    private final Logger log = LoggerFactory.getLogger(GroupController.class);
    private MovieRepository movieRepository;
    private UserRepository userRepository;
    private GenreRepository genreRepository;

    public GroupController(MovieRepository movieRepository,GenreRepository genreRepository,UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/allMovies")
    public List<Movie> retrieveAllMovies() {
    	log.info("Request received to fetch alll movies");
    	List<Movie> listMovie = new ArrayList<Movie>(movieRepository.findAll());
		System.out.println("listMovie all size -" + listMovie.size());
		return listMovie;
		// return new ResponseEntity<List<Movie>>(listMovie,HttpStatus.OK);
        
    }


    @PostMapping("/addMovie")
    ResponseEntity<String> createMovie(@Valid @RequestBody Movie movie_,Errors err) throws URISyntaxException {
        log.info("Request to create movie: {}", movie_);
        
        if(err.hasErrors()){
			return new ResponseEntity<String>(err.getAllErrors()+"",HttpStatus.BAD_REQUEST);
		}
        Movie movie = movieRepository.save(movie_);
        if(movie.getGenre().size() > 0) {
        	for(Genre g: movie.getGenre()) {
        		g.setMovie(movie);
        		genreRepository.save(g);
        		
        	};
        }
		return new ResponseEntity<String>("Movie added successfully with id:" + movie.toString(),HttpStatus.CREATED);
        
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
       
		if (movie != null) {
			return new ResponseEntity<Optional<Movie>>(movie, HttpStatus.OK);
		} else {
			return new ResponseEntity<Optional<Movie>>(HttpStatus.NOT_FOUND);
		}
    }
    
    @PutMapping("/update/{id}")
    ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
        log.info("Request to update movie: {}", movie);
        Movie updatedMovie = movieRepository.save(movie);
        if(updatedMovie.getGenre().size() > 0) {
        	for(Genre g: updatedMovie.getGenre()) {
        		g.setMovie(updatedMovie);
        		genreRepository.save(g);
        		
        	};
        }
		if (updatedMovie == null) {	
			return new ResponseEntity<Movie>(updatedMovie,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Movie>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        log.info("Request to delete movie: {}", id);
        try {
        	movieRepository.deleteById(id);
        	return new ResponseEntity<String>("Employee Deleted successfully with id: " +id,HttpStatus.OK);
        } catch(Exception e) {
        	return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    // for sign up user of find new user
    @PostMapping("/addUser")
    ResponseEntity<String> createMovie(@Valid @RequestBody User user,Errors err) throws URISyntaxException {
        log.info("Request to create new user: {}", user);
        if(err.hasErrors()){
			return new ResponseEntity<String>(err.getAllErrors()+"",HttpStatus.BAD_REQUEST);
		}
        User result = userRepository.save(user);
		return new ResponseEntity<String>("User added successfully with id:" + user.toString(),HttpStatus.CREATED);
    }
    
    @GetMapping("/user/find/{emailId}/{pwd}")
    ResponseEntity<List<User>> createMovie(@PathVariable String emailId,@PathVariable String pwd) throws URISyntaxException {
        log.info("Request to get user by email id user: {}", emailId + pwd);
        List<User> user = userRepository.findByEmailPwd(emailId,pwd);
        
		if (user != null) {
			return new ResponseEntity<List<User>>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<User>>(user, HttpStatus.NOT_FOUND);
		}
    }

}