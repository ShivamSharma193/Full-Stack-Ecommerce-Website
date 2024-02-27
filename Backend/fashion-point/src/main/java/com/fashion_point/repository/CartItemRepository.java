package com.fashion_point.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.CartItem;
import com.fashion_point.pojos.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	
	
	@Query("SELECT c from CartItem c Where c.cart=:cart And c.product=:product And c.size=:size And c.UserId=:userId")
	public CartItem isCartItemExist(@Param("cart")Cart cart, @Param("product")Product product,@Param("size")String size,@Param("userId")Long userId);

}
