package com.fashion_point.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion_point.pojos.OrderItem;
import com.fashion_point.repository.OrderItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemRepository orderItemRepo;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		
		
		return orderItemRepo.save(orderItem);
	}

}
