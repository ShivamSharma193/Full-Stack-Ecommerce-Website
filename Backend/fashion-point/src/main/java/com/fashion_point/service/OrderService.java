package com.fashion_point.service;

import java.util.List;

import com.fashion_point.Exception.OrderException;
import com.fashion_point.pojos.Address;
import com.fashion_point.pojos.OrderD;
import com.fashion_point.pojos.User;

public interface OrderService {
	
	public OrderD CreateOrder(User user, Address shippingAddress);
	
	public OrderD findOrderById(Long OrderId) throws OrderException;
	
	public List<OrderD> usesOrderHistory(Long userId) throws OrderException;
	
	public OrderD placedOrder(Long OrderId) throws OrderException;
	
	public OrderD confirmedOrder(Long OrderId) throws OrderException;
	
	public OrderD shippedOrder(Long OrderId) throws OrderException;
	
	public OrderD deliveredOrder(Long OrderId) throws OrderException;
	
	public OrderD cancledOrder(Long OrderId) throws OrderException;
	
	public List<OrderD>getAllOrders();
	
	public String deleteOrder(Long orderId)throws OrderException;
	

}
