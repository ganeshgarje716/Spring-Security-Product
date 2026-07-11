package com.ganesh.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ganesh.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class Config {
	
	
	
	private final CustomUserDetailsService customUserDetailsService;
	
	
	public Config(CustomUserDetailsService customUserDetailsService) {
		
		this.customUserDetailsService=customUserDetailsService;
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public void authenticateUser() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(customUserDetailsService);
		
		provider.setPasswordEncoder(encoder());
	}
	
	
	
	public void filterChain(HttpSecurity http) {
		
		http.csrf(csrf->csrf.disable());
		
		http.httpBasic(Customizer.withDefaults());
		
		http.authorizeHttpRequests(auth->{
			
			auth.requestMatchers("/api/v1/product/save").hasRole("ADMIN");
			
			auth.requestMatchers("/api/v1/product/all").hasAnyRole("ADMIN", "USER");
			
			auth.requestMatchers("/api/v1/product/byid/**").hasAnyRole("ADMIN", "USER");
			
			auth.requestMatchers("/api/v1/product/update/**").hasRole("ADMIN");
			
			auth.requestMatchers("/api/v1/product/delete/**").hasRole("ADMIN");
			
			auth.requestMatchers("/auth/create-user").permitAll();
		});
	}

}
