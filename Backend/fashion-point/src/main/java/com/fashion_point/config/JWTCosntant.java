package com.fashion_point.config;

public class JWTCosntant {
	public static final String SECRET_KEY="adadwakjdkavcefjfjkfofhcxvbvxvbhh";//random and generatrd by me
	public static final String JWT_HEADER="Authorization";

}
//SECRET_KEY Constant:
//
//SECRET_KEY is a constant field that holds a string representing the secret key used for signing and verifying JWTs. The secret key is a critical component in JWT-based authentication to ensure the integrity and authenticity of the token.
//JWT_HEADER Constant:
//
//JWT_HEADER is a constant field that holds a string representing the name of the HTTP header used to transmit JWTs. In many authentication scenarios, JWTs are included in the HTTP headers of requests. The Authorization header is a common choice for this purpose.
