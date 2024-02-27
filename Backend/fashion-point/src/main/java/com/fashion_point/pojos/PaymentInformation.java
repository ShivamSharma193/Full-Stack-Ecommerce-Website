package com.fashion_point.pojos;

import java.time.LocalDate;

import jakarta.persistence.Column;

//embedded table so not an entity
public class PaymentInformation {
	@Column(name="cardholder_name")
	private String cardholderName;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiration_date")
	private LocalDate exprirationDate;
	
	
	private String cvv;
	
	public PaymentInformation() {
		// TODO Auto-generated constructor stub
	}

	public PaymentInformation(String cardholderName, String cardNumber, LocalDate exprirationDate, String cvv) {
		super();
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.exprirationDate = exprirationDate;
		this.cvv = cvv;
	}

	public String getCardholderName() {
		return cardholderName;
	}

	public void setCardholderName(String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public LocalDate getExprirationDate() {
		return exprirationDate;
	}

	public void setExprirationDate(LocalDate exprirationDate) {
		this.exprirationDate = exprirationDate;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	

}
