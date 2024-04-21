package com.fashion_point.pojos;

import jakarta.persistence.Column;

//it is embedded 
public class Size {
	@Column(name="sizes")
	private S
	public Size() {
		// TODO Auto-generated constructor stub
	}

	public Size(String size, String quantity) {
		super();
		this.size = size;
		this.quantity = quantity;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}

