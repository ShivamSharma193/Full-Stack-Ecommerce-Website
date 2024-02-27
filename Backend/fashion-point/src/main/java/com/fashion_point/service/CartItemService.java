package com.fashion_point.service;

import com.fashion_point.Exception.CartItemException;
import com.fashion_point.Exception.UserException;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.CartItem;
import com.fashion_point.pojos.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;
	
	//if cart item exists then just update quantity
	public CartItem isCartItemExist(Cart cart, Product product, String size,Long userId);
	
	public String removeCartItem(Long userId, Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemid)throws CartItemException;

}
