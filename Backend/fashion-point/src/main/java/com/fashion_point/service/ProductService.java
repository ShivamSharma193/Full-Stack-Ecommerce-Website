package com.fashion_point.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fashion_point.Exception.ProductException;
import com.fashion_point.ReqRes_pojos.CreateProductReq;
import com.fashion_point.pojos.Product;

public interface ProductService {
	
	public Product createProduct(CreateProductReq req);
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public String updateProduct(Long productId,Product product) throws ProductException;
	
	
	
	public Product findProductById(Long id)throws ProductException;
	
	public List<Product> findProductByCategory(String category)throws ProductException;
	
	public Page<Product> getAllProduct(String category, List<String>colors, List<String>sizes, Integer minPrice, Integer maxPrice,
			Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);
	
	public List<Product>findAllProducts();

}
