package com.fashion_point.Controller;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.OrderException;
import com.fashion_point.Exception.UserException;
import com.fashion_point.pojos.Address;
import com.fashion_point.pojos.OrderD;
import com.fashion_point.pojos.User;
import com.fashion_point.service.OrderService;
import com.fashion_point.service.UserServiceInterface;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserServiceInterface userService;
	
	@PostMapping("/")
	public ResponseEntity<OrderD>createOrder(@RequestBody Address shippingAddress,@RequestHeader("Authorization") String JWT )throws UserException{
		User user=userService.findUserbyJWT(JWT);
		
		
		OrderD order=orderService.CreateOrder(user, shippingAddress);
		System.out.println("order"+order);
		// return new ResponseEntity<> (order, HttpStatus.CREATED);
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<OrderD>findOrderByID(@PathVariable("Id")Long OrderId,@RequestHeader("Authorization") String JWT )throws UserException,OrderException{
		
		User user=userService.findUserbyJWT(JWT);
		
		OrderD order=orderService.findOrderById(OrderId);
		
		// return new ResponseEntity<> (order, HttpStatus.FOUND);
			
		
	}
	@GetMapping("/user")
	public ResponseEntity<List<OrderD>>userOrderHistory(@RequestHeader("Authorization") String JWT)throws UserException,OrderException{
		
		
		// User user=userService.findUserbyJWT(JWT);
		
		List<OrderD>orders=orderService.uy(user.getId());
		
		return new ResponseEntity<> (orders, HttpStatus.FOUND);
		
	}
	
	
	

}
