package com.fashion_point.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.ProductException;
import com.fashion_point.ReqRes_pojos.CreateProductReq;
import com.fashion_point.pojos.Product;
import com.fashion_point.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/create")
	public ResponseEntity<Product> createProduct(@RequestBody CreateProductReq req) {
		Product product = productService.createProduct(req);

		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{productId}/delete")
	public HttpStatus DeleteProduct(@PathVariable("productId") Long productId) throws ProductException{
		productService.deleteProduct(productId);

		return HttpStatus.OK;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products=productService.findAllProducts();

		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping("{productId}/update")
	public HttpStatus updateProduct(@PathVariable("productId")Long productId, @RequestBody Product req) throws ProductException{
		productService.updateProduct(productId,req);

		return HttpStatus.CREATED;
	}
	
	//
	
	
	

}
