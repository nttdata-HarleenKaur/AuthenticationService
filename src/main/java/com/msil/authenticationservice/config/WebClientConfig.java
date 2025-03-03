package com.msil.authenticationservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.msil.authenticationservice.client.DMSAuthenticationClient;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebClientConfig {
	
	@Bean
    public WebClient dmsAuthenticationWebClient() {
        return WebClient.builder()
                .baseUrl("")// Set your base URL
               
                .build();
    }

    @Bean
    public DMSAuthenticationClient dmsAuthenticationClient(WebClient webClient) {
    	//HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder()//
            //    .
       // return factory.createClient(DMSAuthenticationClient.class);
    	
    	
    	HttpServiceProxyFactory factory =
    	        HttpServiceProxyFactory.builder(WebClientAdapter.forClient(dmsAuthenticationWebClient()))
    	            .build();
    	    return factory.createClient(DMSAuthenticationClient.class);
    	  
    	
    }
}
