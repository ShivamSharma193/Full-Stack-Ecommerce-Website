package com.fashion_point.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion_point.Exception.UserException;
import com.fashion_point.config.JWTProvider;
import com.fashion_point.pojos.User;
import com.fashion_point.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserImplementation implements UserServiceInterface {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JWTProvider jwtProvider;

	@Override
	public User findUserbyId(Long userid) throws UserException {
		Optional<User> user = userRepo.findById(userid);
		if (user.isPresent()) {
			return user.get();
		}
		throw new UserException("User not found");
	}

	@Override
	public User findUserbyJWT(String jwt) throws UserException {
		String email=jwtProvider.getEmailFromToken(jwt);
		
		User user=userRepo.findByEmail(email);
		
		if (user!=null) {
			return user;
		}
		throw new UserException("User not found with email");
	}
		

}
