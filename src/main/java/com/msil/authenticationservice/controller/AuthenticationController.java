package com.msil.authenticationservice.controller;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msil.authenticationservice.exception.InvalidCredentialsException;
import com.msil.authenticationservice.model.request.UserAuthenticationRequest;
import com.msil.authenticationservice.service.AuthenticationService;
import com.msil.authenticationservice.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController("/api/v1/authentication")
@Slf4j
public class AuthenticationController {
	@Autowired
	AuthenticationService authService;
	private JwtUtil jwtUtil;

    public AuthenticationController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }





	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserAuthenticationRequest authenticationRequest) {
		log.info("inside loginUser Authenticate api");
		
			String username = authenticationRequest.getUserName();
			if (username == null || authenticationRequest.getPassword() == null) {
				throw new InvalidCredentialsException("UserName or Password is Empty");
			}
			String encodedString = Base64.getEncoder().encodeToString(authenticationRequest.getPassword().getBytes());
			// Perform authentication (e.g., check username and password from DMS api)
			authService.getUser(username, encodedString, "D");
			log.info("user authentication from dms api done");
			// Generate JWT token
			String jwt = jwtUtil.generateToken(username);
			
			log.info("jwt token generated for the user" + jwt);
			// save to DynamoDB
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		
	}
}
