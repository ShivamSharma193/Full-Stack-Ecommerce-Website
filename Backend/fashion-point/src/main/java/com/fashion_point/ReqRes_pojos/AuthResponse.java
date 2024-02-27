package com.fashion_point.ReqRes_pojos;

public class AuthResponse {
	private String message;
	private String jwt;
	
	public AuthResponse() {
		// TODO Auto-generated constructor stub
	}

	public AuthResponse(String message, String jwt) {
		super();
		this.message = message;
		this.jwt = jwt;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	@Override
	public String toString() {
		return "AuthResponse [message=" + message + ", jwt=" + jwt + "]";
	}
	

}
