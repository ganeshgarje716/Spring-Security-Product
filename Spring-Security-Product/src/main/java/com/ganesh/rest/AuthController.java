package com.ganesh.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.entity.User;
import com.ganesh.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	

	private final UserRepository userRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public AuthController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		
		this.userRepository=userRepository;
		
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	
	
	
	public ResponseEntity<User> createUsers(@RequestBody User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		if (user.getUsername().equals("Ganesh")) {
			
			user.setRole("ADMIN");
		}
		else {
			
			user.setRole("USER");
		}
		
		User saved = userRepository.save(user);
		
		return new ResponseEntity<User>(saved, HttpStatus.CREATED);
	}
	
}
