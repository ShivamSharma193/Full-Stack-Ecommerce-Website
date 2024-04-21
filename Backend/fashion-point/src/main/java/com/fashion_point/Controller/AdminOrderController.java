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
	public 
		OrderD order=orderService.confirmedOrder(orderId);
		
		return new ResponseEntity<OrderD>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{OrderID}/ship")
	public ROrderD>(order,HttpStatus.OK);
	}
	
	@PutMapping("/{OrderID}/deliver")
	public ResponseEntity<OrderD>deliverOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String JWT)
	throws OrderExcepti
		return new ResponseEntity<OrderD>(order,HttpStatus.OK);
	}
	
	@DeleteMapping("/{OrderID}/delete")
	public HttpStatus DeleteOrderHandler(@PathVariable Long orderId,@RequestHeader("Authorization") String JWT)
	throws OrderException{
		orderService.deleteOrder(orderId);
		
		
		return HttpStatus.OK;
	}
	
	

}
