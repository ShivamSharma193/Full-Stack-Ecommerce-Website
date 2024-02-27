package com.fashion_point.ReqRes_pojos;

public class AddItemRequest {
	
	private Long productId;
	
	private String Size;
	
	private int qunatity;
	
	private Integer price;
	
	public AddItemRequest() {
		// TODO Auto-generated constructor stub
	}

	public AddItemRequest(Long productId, String size, int qunatity, Integer price) {
		super();
		this.productId = productId;
		Size = size;
		this.qunatity = qunatity;
		this.price = price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public int getQunatity() {
		return qunatity;
	}

	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	

}
