package com.fashion_point.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion_point.Exception.CartItemException;
import com.fashion_point.Exception.UserException;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.CartItem;
import com.fashion_point.pojos.Product;
import com.fashion_point.pojos.User;
import com.fashion_point.repository.CartItemRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class CartItemImpl implements CartItemService{
	@Autowired
	private CartItemRepository cartItemRepo;
	@Autowired
	private UserServiceInterface userService;
	
//	@Autowired
//	private CartRepository cartRepo;

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		//productprice x quantity
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		
		CartItem saveCartItem=cartItemRepo.save(cartItem);
		
		return saveCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		
		CartItem item=findCartItemById(id);
		
		//if item is null user is null 
		User user=userService.findUserbyId(item.getUserId());
		
		if(user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getProduct().getPrice()*item.getQuantity());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
			
			cartItemRepo.save(item);
		}
		else {
			throw new UserException("Cant update another user's item");
		}
		
		return item;
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		CartItem cartItem=cartItemRepo.isCartItemExist(cart, product, size, userId);
		
		return cartItem;
	}

	@Override
	public String removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
		CartItem item=findCartItemById(cartItemId);
		User user=userService.findUserbyId(item.getUserId());
		
		User reqUser= userService.findUserbyId(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			
			cartItemRepo.deleteById(cartItemId);
			
		}
		else {
			throw new UserException("Cant remove another user's item");
			
		}
		
		return "CartItemRemoved";
	}

	@Override
	public CartItem findCartItemById(Long cartItemid) throws CartItemException {
		Optional<CartItem> item=cartItemRepo.findById(cartItemid);
		
		if(item.isPresent()) {
			return item.get();
		}
		 throw new CartItemException("no item found");
	}

}
