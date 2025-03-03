package com.msil.authenticationservice.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.msil.authenticationservice.model.response.UserAuthenticationResponse;

import reactor.core.publisher.Mono;

@HttpExchange("/api/v1/dmsauthentication")
public interface DMSAuthenticationClient {
	@GetExchange( value = "/userDMSAuth/{id}/{password}/{userType}")
    Mono<UserAuthenticationResponse> getUser(@PathVariable("id") String userId,@PathVariable("password") String password,@PathVariable("userType") String userType);
}
