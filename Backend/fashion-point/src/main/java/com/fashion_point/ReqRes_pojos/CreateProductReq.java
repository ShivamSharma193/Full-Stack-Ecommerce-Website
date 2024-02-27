package com.fashion_point.ReqRes_pojos;

import java.util.HashSet;
import java.util.Set;

import com.fashion_point.pojos.Size;


public class CreateProductReq {
	
private String title;
	
	private String description;
	
	private int price;
	
	private int discountedPrice;
	
	private int discountPercent;
	
	private int quantity;
	
	private String brand;
	
	private String color;
	
	private Set<Size>sizes=new HashSet<>();
	
	private String imageSrc;
	
	private String topLevelCategory;
	
	private String secondLevelCategory;
	private String thirdLevelCategory;
	
	
	public CreateProductReq() {
		// TODO Auto-generated constructor stub
	}


	public CreateProductReq(String title, String description, int price, int discountedPrice, int discountPercent,
			int quantity, String brand, String color, Set<Size> sizes, String imageSrc, String topLevelCategory,
			String secondLevelCategory, String thirdLevelCategory) {
		super();
		this.title = title;
		this.description = description;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.discountPercent = discountPercent;
		this.quantity = quantity;
		this.brand = brand;
		this.color = color;
		this.sizes = sizes;
		this.imageSrc = imageSrc;
		this.topLevelCategory = topLevelCategory;
		this.secondLevelCategory = secondLevelCategory;
		this.thirdLevelCategory = thirdLevelCategory;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public int getDiscountedPrice() {
		return discountedPrice;
	}


	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}


	public int getDiscountPercent() {
		return discountPercent;
	}


	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public Set<Size> getSizes() {
		return sizes;
	}


	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}


	public String getImageSrc() {
		return imageSrc;
	}


	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}


	public String getTopLevelCategory() {
		return topLevelCategory;
	}


	public void setTopLevelCategory(String topLevelCategory) {
		this.topLevelCategory = topLevelCategory;
	}


	public String getSecondLevelCategory() {
		return secondLevelCategory;
	}


	public void setSecondLevelCategory(String secondLevelCategory) {
		this.secondLevelCategory = secondLevelCategory;
	}


	public String getThirdLevelCategory() {
		return thirdLevelCategory;
	}


	public void setThirdLevelCategory(String thirdLevelCategory) {
		this.thirdLevelCategory = thirdLevelCategory;
	}


	
	
	

}
