package com.fashion_point.service;

import com.fashion_point.Exception.ProductException;
import com.fashion_point.ReqRes_pojos.AddItemRequest;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.User;

public interface CartService {
	public Cart CreateCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
