package com.fashion_point.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
i
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
	
	
//gonohne
g
fghgtryg
fdsfg
yuf
ry
kjf
gk
gfgk
erqw
,k
yr3r
kyu
tre
y
re
,jfhr
e
m
55,

kf
4
, 

 
}
