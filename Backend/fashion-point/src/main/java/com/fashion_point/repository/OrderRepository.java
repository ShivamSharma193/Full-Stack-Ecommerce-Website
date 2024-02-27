package com.fashion_point.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fashion_point.pojos.OrderD;

public interface OrderRepository extends JpaRepository<OrderD, Long>{
	
	//we have to write a method to fetch all orders for a particular user which are placed or confirmed
	
	@Query("Select o from OrderD o where o.user.id=:userId And(o.orderStatus='PLACED' OR o.orderStatus='CONFIRMED' OR o.orderStatus='SHIPPED' OR o.orderStatus='DELIVERED')")
	public List<OrderD>getUserOrders(@Param("userId")Long userId);

}
