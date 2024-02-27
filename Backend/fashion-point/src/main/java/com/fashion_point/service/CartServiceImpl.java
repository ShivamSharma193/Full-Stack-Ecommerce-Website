package com.fashion_point.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fashion_point.Exception.ProductException;
import com.fashion_point.ReqRes_pojos.AddItemRequest;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.CartItem;
import com.fashion_point.pojos.Product;
import com.fashion_point.pojos.User;
import com.fashion_point.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository cartRepo;
	
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private ProductService productService;
	

	@Override
	public Cart CreateCart(User user) {
		//as cart can be empty so creating new insatnce for each user
		Cart cart=new Cart();
		cart.setUser(user);
		
		Cart savedCart= cartRepo.save(cart);
		
		return savedCart;
	}

	@Override
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart=cartRepo.findByUserId(userId);
		Product product=productService.findProductById(req.getProductId());
		
		CartItem isPresent=cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		if(isPresent==null) {
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQunatity());
			cartItem.setUserId(userId);
			int price=req.getQunatity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem= cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			
			
		}
		return "Cart Item Added";
	}

	//below method to show final amount for cart
	@Override
	public Cart findUserCart(Long UserId) {
		Cart cart=cartRepo.findByUserId(UserId);
		int totalPrice=0,totalDiscountedPrice=0,totalItem=0;
		
		for(CartItem cartItem:cart.getCartItems()) {
			totalPrice=totalPrice+cartItem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartItem.getDiscountedPrice();
			totalItem=totalItem+cartItem.getQuantity();
		}
		
		cart.setTotalPrice(totalPrice);
		cart.setTotalDiscountPrice(totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		
		return  cartRepo.save(cart);
	}

}
