package com.fashion_point.pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Fetch;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String role;
	private String mobile;
	private LocalDateTime createdOn;
	//mapped by is used when i is bidirectional relation
	//user is owning side so passed in mapped by
	//cascade = CascadeType.ALL   because if user is deleted all the addresses that particular user has
	//must be deleted
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<Address>address=new ArrayList<>();
	
	@Embedded
	@ElementCollection(fetch = FetchType.EAGER) //so that new table will be created
	
	@CollectionTable(name="payment_information",joinColumns = @JoinColumn(name="user_id"))
	
	private List<PaymentInformation> paymentInformation=new ArrayList<>();
	
	
	
	//ratings and review not implemented in frontend
	//@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	//@JsonIgnore
	//private List<Rating>ratings=new ArrayList<>();
	
	//@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	//@JsonIgnore
	//private List<Review>reviews=new ArrayList<>();
	
	public User() {
		// TODO Auto-generated constructor stub
	}



	public User(Long id, String firstName, String lastName, String password, String email, String role, String mobile,
			LocalDateTime createdOn, List<Address> address, List<PaymentInformation> paymentInformation) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.role = role;
		this.mobile = mobile;
		this.createdOn = createdOn;
		this.address = address;
		this.paymentInformation = paymentInformation;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public LocalDateTime getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}



	public List<Address> getAddress() {
		return address;
	}



	public void setAddress(List<Address> address) {
		this.address = address;
	}



	public List<PaymentInformation> getPaymentInformation() {
		return paymentInformation;
	}



	public void setPaymentInformation(List<PaymentInformation> paymentInformation) {
		this.paymentInformation = paymentInformation;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", role=" + role + ", mobile=" + mobile + ", createdOn=" + createdOn
				+ ", address=" + address + ", paymentInformation=" + paymentInformation + "]";
	}
	
	
	

}
