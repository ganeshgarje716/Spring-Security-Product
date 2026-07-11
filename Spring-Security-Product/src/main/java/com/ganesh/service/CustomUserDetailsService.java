package com.ganesh.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ganesh.entity.User;
import com.ganesh.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService{
	
	
	
	private final UserRepository userRepository;
	
	
	public CustomUserDetailsService(UserRepository userRepository) {
		
		this.userRepository=userRepository;
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User> byUsername = userRepository.findByUsername(username);
		
		if (byUsername.isPresent()) {
			
			User user = byUsername.get();
			
			return org.springframework.security.core.userdetails.User
					.withUsername(username)
					.password(user.getPassword())
					.roles(user.getRole())
					.build();
		}
		
		
		throw new UsernameNotFoundException("User Not Found With UserName = "+username);
	}

}
