package com.fashion_point.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTValidator extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//declared in jwtconstat file
		
		String jwt=request.getHeader(JWTCosntant.JWT_HEADER);
		
		if(jwt!=null) {
			//Removes the "Bearer " prefix from the JWT, assuming that it is stored with this prefix.
			//For example, if jwt is "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", 
			//then after jwt = jwt.substring(7);, jwt will be "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
			jwt=jwt.substring(7);
			try {
				//HmacSHA256 algorithm
				SecretKey key=Keys.hmacShaKeyFor(JWTCosntant.SECRET_KEY.getBytes());
				
				Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
				
				String email=String.valueOf(claims.get("email"));
				String authorities=String.valueOf(claims.get("authorities"));
				
				List<GrantedAuthority> auths=AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication=new UsernamePasswordAuthenticationToken(email,null, auths);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			catch(Exception e) {
				throw new BadCredentialsException("Invalid TOken form JWTValidator");
				
			}
		}
		filterChain.doFilter(request, response);
	}

}
