package com.booking.movieservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.booking.movieservices.model.Movie;
import com.booking.movieservices.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
	@Query(value = "SELECT * FROM users u where u.email = :email AND u.password = :password", 
	        nativeQuery=true)
    List<User> findByEmailPwd(@Param("email") String title, 
            @Param("password") String description);
}