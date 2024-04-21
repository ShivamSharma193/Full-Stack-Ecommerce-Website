package com.fashion_point.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.ProductException;
import com.fashion_point.Exception.UserException;
import com.fashion_point.ReqRes_pojos.AddItemRequest;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.User;
import com.fashion_point.service.CartService;
import com.fashion_point.service.UserServiceInterface;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	
	@Autowired
	private UserServiceInterface userService;
	
	@GetMapping("/")
	public ResponseEntity<Cart>findUserByCart(@RequestHeader("Authorization")String jwt)throws UserException{
		
		User user=userService.findUserbyJWT(jwt);
		// Cart cart=cartService.findUserCart(user.getId());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public HttpStatus addToCart(@RequestBody AddItemRequest req,@RequestHeader("Authorization")String jwt)throws UserException,ProductException{
		User user=userService.findUserbyJWT(jwt);
		cartService.addCartItem(user.getId(), req);
		return HttpStatus.CREATED;
	}
	
}
