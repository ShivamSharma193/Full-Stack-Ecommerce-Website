package com.fashion_point.config;

import java.util.Arrays;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

//session creation policy bydefault adds credentials into cookies so to avoid it STATELESS 
		//is used below
		//line below code says create a stateless session creation policy and then authorize any http request
				//which starts with /api/ and authenticate it rest all resquest are permitted
				//add filter before is used to check if incoming rquest is getting validated or not
		//CSRF stands for Cross-Site Request Forgery. It is a type of security vulnerability that occurs when an attacker tricks a 
		//user's browser into making an unintended and potentially harmful request to a web application 
		//on which the user is authenticated. so disabled it
		//CORS stands for Cross-Origin Resource Sharing. 
		//It is a security feature implemented by web browsers to control and restrict web pages 
		//from making requests to a different domain than the one that served the original web page
        //so we need to condigure it by .configurationSource() which is a method which takes parameter
       // as instantiation of a interface to be implemented by classes (usually HTTP request handlers) thatprovides a 
          //CorsConfiguration instance based on the provided request.
 
@Configuration
public class AppConfig {
	@Bean
	public SecurityFilterChain securityFilterchain(HttpSecurity http) throws Exception{
		
		
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**")
				.authenticated().anyRequest().permitAll()).addFilterBefore(new JWTValidator(), BasicAuthenticationFilter.class).csrf().disable()
		.cors().configurationSource(new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration cfg=new CorsConfiguration();
				
				//which websites can access this backend takes list of strings of all websites as input
				cfg.setAllowedOrigins(Arrays.asList(
						//react origin 
						"http://localhost:3000"
						));
				//this is to allow get post put delete and any kind of incming method 
				cfg.setAllowedMethods(Collections.singletonList("*"));
				cfg.setAllowCredentials(true);
				cfg.setAllowedHeaders(Collections.singletonList("*"));
				cfg.setExposedHeaders(Arrays.asList("Authorization"));
				cfg.setMaxAge(3600L);
				return cfg;
			}
		})
		.and().httpBasic().and().formLogin();
		
		
		
		return http.build();
		//Builds the object and returns it or null.
		
	}
	//when user enters password it should be hashed first
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
