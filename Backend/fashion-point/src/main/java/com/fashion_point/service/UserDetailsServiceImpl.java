package com.fashion_point.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fashion_point.pojos.User;
import com.fashion_point.repository.UserRepository;

import jakarta.transaction.Transactional;

//customer
//service for signup and username is email id for signup and login
//UserDetailsService is a spring security interface which provides below method
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
	
	//@Autowired 
	private UserRepository userRepo;
	//alternate for autowired is creating instance by ourself
	public UserDetailsServiceImpl(UserRepository userRepo) {
		this.userRepo=userRepo;
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=userRepo.findByEmail(username);
		
		if(user== null) {
			throw new UsernameNotFoundException("User Not Found  please check "+username);
		}
		List<GrantedAuthority>authorities= new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}

}
