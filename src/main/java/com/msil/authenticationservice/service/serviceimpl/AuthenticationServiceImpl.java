package com.msil.authenticationservice.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.msil.authenticationservice.client.DMSAuthenticationClient;
import com.msil.authenticationservice.model.response.UserAuthenticationResponse;
import com.msil.authenticationservice.service.AuthenticationService;

import reactor.core.publisher.Mono;

public class AuthenticationServiceImpl implements  AuthenticationService{

	@Autowired
    private DMSAuthenticationClient dmsAuthenticationClient;
	
	public Mono<UserAuthenticationResponse> getUser(String userId,String password,String userType) {
		return dmsAuthenticationClient.getUser(userId,password,userType);
	}
}
