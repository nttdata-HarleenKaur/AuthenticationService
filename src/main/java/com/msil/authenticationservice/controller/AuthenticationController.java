package com.msil.authenticationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.msil.authenticationservice.model.request.AuthenticationRequest;
import com.msil.authenticationservice.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController("/api/v1/authentication")
@Slf4j
public class AuthenticationController {
	private JwtUtil jwtUtil;

    public AuthenticationController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }





	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			String username = authenticationRequest.getUserName();
			if (username == null || authenticationRequest.getPassword() == null) {
				throw new Exception("UserName or Password is Empty");
			}
			// Perform authentication (e.g., check username and password from DMS api)
			// Generate JWT token
			String jwt = jwtUtil.generateToken(username);
			log.info("jwt token generated for the user" + jwt);
			// save to DynamoDB
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}
