package com.fashion_point.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion_point.Exception.OrderException;
import com.fashion_point.pojos.Address;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.CartItem;
import com.fashion_point.pojos.OrderD;
import com.fashion_point.pojos.OrderItem;
import com.fashion_point.pojos.User;
import com.fashion_point.repository.AddressRepository;
import com.fashion_point.repository.CartRepository;
import com.fashion_point.repository.OrderItemRepository;
import com.fashion_point.repository.OrderRepository;
import com.fashion_point.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private CartService cartService;
	@Autowired
	private AddressRepository addressRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private OrderItemRepository orderItemRepo;

	@Override
	public OrderD CreateOrder(User user, Address shippingAddress) {
		//link passed address to passed user
		shippingAddress.setUser(user);
		//save shipping address in address table 
		Address address=addressRepo.save(shippingAddress);
		//in user table it has list of addresses for each user add above address in list of address
		user.getAddress().add(address);
		userRepo.save(user);
		
		//to make order we need to accress cart of that user nd store it in cart type of var
		Cart cart=cartService.findUserCart(user.getId());
		
		//create new OrderItems instance 
		List<OrderItem>orderItems=new ArrayList<OrderItem>();
		
		//add cartItems in above list
		for(CartItem item:cart.getCartItems()) {
			
			//create orderitem instance and store each cartitem in it and add oder item in order
			OrderItem orderItem=new OrderItem();
			//set values of cartItems in order Item
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			OrderItem createOrderItem=orderItemRepo.save(orderItem);
			
			orderItems.add(createOrderItem);
			
		}
		//now we have order items and cart so now create order by adding order item in order and take required cart details 
		OrderD createOrder=new OrderD();
		createOrder.setUser(user);
		createOrder.setOrderItems(orderItems);
		createOrder.setDiscount(cart.getDiscount());
		createOrder.setTotalPrice(cart.getTotalPrice());
		createOrder.setTotalDisountedPrice(cart.getTotalDiscountPrice());
		createOrder.setTotalItem(cart.getTotalItem());
		
		createOrder.setShippingAddress(address);
		createOrder.setOrderDate(LocalDateTime.now());
		createOrder.setOrderStatus("PENDING"); //as payment not inititaed
		createOrder.getPaymentDetails().setStatus("PENDING");
		createOrder.setCreatedDate(LocalDateTime.now());
		
		OrderD saveOrder=orderRepo.save(createOrder);
		
		//for each item of Order items add it in order
		//Associate OrderItems with the Saved Order:
		for(OrderItem item:orderItems) {
			item.setInOrder(saveOrder);
			
			orderItemRepo.save(item);
		}
		
		
		return saveOrder;
	}

	@Override
	public OrderD findOrderById(Long OrderId) throws OrderException {
		
		Optional<OrderD>order=orderRepo.findById(OrderId);
		if(order.isPresent()) {
			return order.get();
		}
		throw new OrderException("Order does not exist ");
	}

	@Override
	public List<OrderD> usesOrderHistory(Long userId) throws OrderException {
		List<OrderD>allOrders=orderRepo.getUserOrders(userId);
		
		return allOrders;
	}

	@Override
	public OrderD placedOrder(Long OrderId) throws OrderException {
		
		OrderD order=findOrderById(OrderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return orderRepo.save(order);
	}

	@Override
	public OrderD confirmedOrder(Long OrderId) throws OrderException {
		OrderD order=findOrderById(OrderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepo.save(order);
	}

	@Override
	public OrderD shippedOrder(Long OrderId) throws OrderException {
		OrderD order=findOrderById(OrderId);
		order.setOrderStatus("SHIPPED");
		return orderRepo.save(order);
	}

	@Override
	public OrderD deliveredOrder(Long OrderId) throws OrderException {
		OrderD order=findOrderById(OrderId);
		order.setOrderStatus("DELIVERED");
		return orderRepo.save(order);
	}

	@Override
	public OrderD cancledOrder(Long OrderId) throws OrderException {
		OrderD order=findOrderById(OrderId);
		order.setOrderStatus("CANCELLED");
		return orderRepo.save(order);
	}

	@Override
	public List<OrderD> getAllOrders() {
		List<OrderD> orders=orderRepo.findAll();
		return orders;
	}

	//admin
	@Override
	public String deleteOrder(Long orderId) throws OrderException {
		OrderD order=findOrderById(orderId);
			orderRepo.deleteById(orderId);
			return "Order has been deleted";
		
		}

}
