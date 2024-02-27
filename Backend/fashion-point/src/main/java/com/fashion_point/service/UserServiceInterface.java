package com.fashion_point.service;

import com.fashion_point.Exception.UserException;
import com.fashion_point.pojos.User;

public interface UserServiceInterface {
	public User findUserbyId(Long userid)throws UserException;
	
	public User findUserbyJWT(String jwt)throws UserException;

}
