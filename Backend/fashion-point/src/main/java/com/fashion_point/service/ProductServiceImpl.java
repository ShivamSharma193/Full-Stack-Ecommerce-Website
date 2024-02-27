package com.fashion_point.service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fashion_point.Exception.ProductException;
import com.fashion_point.ReqRes_pojos.CreateProductReq;
import com.fashion_point.pojos.Category;
import com.fashion_point.pojos.Product;
import com.fashion_point.repository.CategoryRepository;
import com.fashion_point.repository.ProductRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productrepo;
	
	@Autowired
	private UserServiceInterface userService;
	
	@Autowired
	private CategoryRepository categoryrepo;

	@Override
	public Product createProduct(CreateProductReq req) {
		//define  category first
		//store user given category name in a var
		Category topLevel=categoryrepo.findByName(req.getTopLevelCategory());
		
		//check if exists if not then create a new one and save it
		if(topLevel==null) {
			Category topLevelCategory= new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);
			
			topLevel=categoryrepo.save(topLevelCategory);
		}
		//same above procedure for second level category
		Category secondLevel=categoryrepo.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
		if(secondLevel==null) {
			Category secondLevelCategory= new Category();
			secondLevelCategory.setName(req.getSecondLevelCategory());
			secondLevelCategory.setParentCategory(topLevel);
			secondLevelCategory.setLevel(2);
			
			secondLevel=categoryrepo.save(secondLevelCategory);
		}
		
		//for third level
		Category thirdLevel=categoryrepo.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
		if(thirdLevel==null) {
			Category thirdLevelCategory= new Category();
			thirdLevelCategory.setName(req.getThirdLevelCategory());
			thirdLevelCategory.setParentCategory(secondLevel);
			thirdLevelCategory.setLevel(3);
			
			thirdLevel=categoryrepo.save(thirdLevelCategory);
		}
		//now create a product
		Product product=new Product();
		
		
		product.setTitle(req.getTitle());
		product.setDescription(req.getDescription());
		product.setColor(req.getColor());
		product.setBrand(req.getBrand());
		product.setCategory(thirdLevel);
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDiscountPercent(req.getDiscountPercent());
		product.setImageSrc(req.getImageSrc());
		product.setPrice(req.getPrice());
		product.setSizes(req.getSizes());
		product.setQuantity(req.getQuantity());
		product.setCreatedAt(LocalDateTime.now());
		
		//save product
		Product SaveProduct=productrepo.save(product);
		
		return SaveProduct;
		
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		
		Product product=findProductById(productId);
		
		if(product==null) {
			throw new ProductException("Product does not Exist");
		}
		//in size product is as foreign key 
		product.getSizes().clear();
		productrepo.delete(product);
		return "Product Deleted Successfully";
	}

	@Override
	public String updateProduct(Long productId, Product product) throws ProductException {
		Product productUpdate=findProductById(productId);
		if(product.getQuantity()!=0) {
			productUpdate.setQuantity(product.getQuantity());
		}
		productrepo.save(productUpdate);
		return "Product Updated";
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
		//Use Optional when you have a method that may or may not return a value, and you want to make it clear that the result could be absent.
		Optional<Product>optional=productrepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
			
		}
		throw new ProductException("Product not found by given ID");
	}

	@Override
	public List<Product> findProductByCategory(String category) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//badme make this method as list method
	//sb page ka hatao and bs products return karo
	public Page<Product> getAllProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {
		
		Pageable pageable=PageRequest.of(pageNumber,pageSize);
		
		//get list of products after filterProducts query defined in productrepository is called 
		List<Product>products=productrepo.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		if(!colors.isEmpty()) {
			//pass above products in stream 
			//filter by colours. As there are multiple colours of products pass them by stream as well 
			//var p is iterator of list of colours users has passed
			//var c is iterator of colors stream passed
			//any match will return if any of colour matches with the list of colours user has set 
			//equals ignore case will ignore casing of letters
			//now this will be collected in form of list and stored in products
			
			products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
		if(stock!=null) {
			if(stock.equals("in_stock")){
				//pass products in stream.
				//filter: get products whose quantity is greater than 0 
				//collect the filtered products in list and store in Products var
				products=products.stream().filter(p -> p.getQuantity()>0).collect(Collectors.toList());
				
			}
			else if(stock.equals("out_of_stock")) {
				products=products.stream().filter(p ->p.getQuantity()<=0).collect(Collectors.toList());
			}
		}
		//not applocable for my project as i have only 1 page for each category of products
		int startIndex=(int)pageable.getOffset();
		int endIndex=Math.min(startIndex+ pageable.getPageSize(),products.size());
		
		List<Product>pageContent=products.subList(startIndex, endIndex);
		
		Page<Product>filteredProducts=new PageImpl<>(pageContent,pageable,products.size());
		
		return filteredProducts;
	}

	@Override
	public List<Product> findAllProducts() {
		
		return productrepo.findAll();
	}
	

}
