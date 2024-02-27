package com.fashion_point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fashion_point.pojos.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
	
	

}
