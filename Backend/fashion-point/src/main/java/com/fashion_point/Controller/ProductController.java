package com.fashion_point.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.ProductException;
import com.fashion_point.pojos.Product;
import com.fashion_point.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,

			@RequestParam List<String> color, @RequestParam List<String> sizes, @RequestParam Integer minPrice,

			@RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort,
			@RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

		Page<Product> res = productService.getAllProduct(category, color, sizes, minPrice, maxPrice, minDiscount, sort,
				stock, pageNumber, pageSize);

		return new ResponseEntity<Page<Product>>(res, HttpStatus.ACCEPTED);

	}
	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product>findProductByIdHandler(@PathVariable Long productId )throws ProductException{
		
		Product product =productService.findProductById(productId);
		
		return new ResponseEntity<>(product,HttpStatus.OK);
		
	}
	

}
