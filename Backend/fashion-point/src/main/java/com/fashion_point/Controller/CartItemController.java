package com.fashion_point.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.CartItemException;
import com.fashion_point.Exception.UserException;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.CartItem;
import com.fashion_point.pojos.User;
import com.fashion_point.service.CartItemService;
import com.fashion_point.service.UserServiceInterface;

@RestController
@RequestMapping("/api/cart_items")
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private UserServiceInterface userService;

	@DeleteMapping("/{cartItemId}")
	public HttpStatus removefromCartHandler(@PathVariable Long cartItemId, @RequestHeader("Authorization") String JWT)
			throws UserException, CartItemException {

		User user = userService.findUserbyJWT(JWT);

		cartItemService.removeCartItem(user.getId(), cartItemId);

		return HttpStatus.OK;
	}

	@PutMapping("/{cartItemId}")
	public ResponseEntity<CartItem> UpdateCartHandler(@RequestBody CartItem cartItem, @PathVariable Long cartItemId,
			@RequestHeader("Authorization") String JWT) throws UserException, CartItemException {
		User user = userService.findUserbyJWT(JWT);
		CartItem updatedCartItem=cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
		
		return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED);
	}

}
