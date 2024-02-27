package com.fashion_point.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.OrderException;
import com.fashion_point.pojos.OrderD;
import com.fashion_point.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<OrderD>>getAllOrdersHandler(){
		List<OrderD>orders=orderService.getAllOrders();
		return new ResponseEntity<List<OrderD>>(orders,HttpStatus.ACCEPTED);
	}
	
	//to update orrder status ie: to confirm order
	@PutMapping("/{OrderID}/confirm")
	public ResponseEntity<OrderD>ConfirmOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String JWT)
	throws OrderException{
		OrderD order=orderService.confirmedOrder(orderId);
		
		return new ResponseEntity<OrderD>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{OrderID}/ship")
	public ResponseEntity<OrderD>ShipOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String JWT)
	throws OrderException{
		OrderD order=orderService.shippedOrder(orderId);
		
		return new ResponseEntity<OrderD>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{OrderID}/deliver")
	public ResponseEntity<OrderD>deliverOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String JWT)
	throws OrderException{
		OrderD order=orderService.deliveredOrder(orderId);
		
		return new ResponseEntity<OrderD>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{OrderID}/cancel")
	public ResponseEntity<OrderD>CancelOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String JWT)
	throws OrderException{
		OrderD order=orderService.cancledOrder(orderId);
		
		return new ResponseEntity<OrderD>(order,HttpStatus.OK);
	}
	
	@DeleteMapping("/{OrderID}/delete")
	public HttpStatus DeleteOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String JWT)
	throws OrderException{
		orderService.deleteOrder(orderId);
		
		
		return HttpStatus.OK;
	}
	
	

}
