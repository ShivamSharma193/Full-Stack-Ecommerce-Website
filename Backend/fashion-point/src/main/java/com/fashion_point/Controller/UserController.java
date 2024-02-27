package com.fashion_point.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.UserException;
import com.fashion_point.pojos.User;
import com.fashion_point.service.UserServiceInterface;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserServiceInterface userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User>getUserHandler(@RequestHeader("Authorization")String JWT)throws UserException{
		
		User user=userService.findUserbyJWT(JWT);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}

}
