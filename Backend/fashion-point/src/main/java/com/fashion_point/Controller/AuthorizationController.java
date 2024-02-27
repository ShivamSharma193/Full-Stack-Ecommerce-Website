package com.fashion_point.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fashion_point.Exception.UserException;
import com.fashion_point.ReqRes_pojos.AuthResponse;
import com.fashion_point.ReqRes_pojos.LoginRequest;
import com.fashion_point.config.JWTProvider;
import com.fashion_point.pojos.Cart;
import com.fashion_point.pojos.User;
import com.fashion_point.repository.UserRepository;
import com.fashion_point.service.CartService;
import com.fashion_point.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserDetailsServiceImpl userService;
    @Autowired
	private JWTProvider jwtProvider;
	// to hash password
	@Autowired
	private PasswordEncoder passwordEncoder;
	// method for signup
	@Autowired
	private CartService cartService;

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException  {
		// store incoming data in resp vars
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		// check if user email already exists
		User emailExists = userRepo.findByEmail(email);
		if (emailExists != null) {
			throw new UserException("Email Already taken enter another Username please!");
		}

		// else make a new user
		User createUser = new User();
		createUser.setEmail(email);
		createUser.setPassword(passwordEncoder.encode(password));
		createUser.setFirstName(firstName);
		createUser.setLastName(lastName);

		// jpa method to save no need off implementation
		User saveCreateUser = userRepo.save(createUser);
		
		//create cart as soon as user is made
		Cart cart=cartService.CreateCart(saveCreateUser);

		Authentication authenticate = new UsernamePasswordAuthenticationToken(saveCreateUser.getEmail(),
				saveCreateUser.getPassword());

		// set above in securitycontext holder
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		String token = jwtProvider.generateToken(authenticate);

		AuthResponse authResponse = new AuthResponse("SignUp Success", token);

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

	}

	// login method
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest loginRequest) throws UserException {
		String username = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		Authentication authenticate = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		
		
		String token = jwtProvider.generateToken(authenticate);

		AuthResponse authResponse = new AuthResponse("Signin Success", token);

		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

	}

	// check if username and password matches called from above method
	private Authentication authenticate(String username, String password) {
		UserDetails userDetails = userService.loadUserByUsername(username);

		if (userDetails == null) {
			throw new BadCredentialsException("Username is Invalid");
		}
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password is Invalid");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
