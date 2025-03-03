package com.msil.authenticationservice.service;

import org.springframework.stereotype.Service;

import com.msil.authenticationservice.model.response.UserAuthenticationResponse;

import reactor.core.publisher.Mono;
@Service
public interface AuthenticationService {
	Mono<UserAuthenticationResponse> getUser(String userId,String password,String userType);
}
