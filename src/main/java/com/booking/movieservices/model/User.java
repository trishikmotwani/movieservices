package com.booking.movieservices.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "users")
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7932508226809937746L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long user_id;
	
    private String name;
    
    @Column(unique = true)
    private String email;
    
    private String password;
    private boolean isAdmin;
    
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Long getId() {
		return user_id;
	}
	public void setId(Long user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
}
