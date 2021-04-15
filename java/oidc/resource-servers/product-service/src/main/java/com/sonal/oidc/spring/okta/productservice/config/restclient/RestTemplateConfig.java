package com.sonal.oidc.spring.okta.productservice.config.restclient;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import com.sonal.oidc.spring.okta.productservice.filter.JWTStore;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class RestTemplateConfig {
	
	@Autowired
	private JWTStore jwtStore;
	
	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.setInterceptors(Collections.singletonList(restTemplateHeaderInterceptor()));
		
		return restTemplate;
	}
	
	@Bean
	@RequestScope
	public ClientHttpRequestInterceptor restTemplateHeaderInterceptor() {
		
		return (request, body, execution) -> {
			
			request.getHeaders().add(HttpHeaders.AUTHORIZATION, jwtStore.getJwt());
			request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			
			log.info("******** Current JWT ************ " + jwtStore.getJwt());
			
			ClientHttpResponse response = execution.execute(request, body);
			
			return response;
		};
	}
	
	
}
