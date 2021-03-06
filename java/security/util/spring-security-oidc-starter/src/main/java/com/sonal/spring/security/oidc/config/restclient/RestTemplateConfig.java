package com.sonal.spring.security.oidc.config.restclient;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import com.sonal.spring.security.oidc.config.filter.JWTStore;

import lombok.extern.slf4j.Slf4j;


@ConditionalOnProperty(name="myorg.security.oidc.enabled", havingValue="true")
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
			
			if(!ObjectUtils.isEmpty(jwtStore.getJwt())) {
				request.getHeaders().add(HttpHeaders.AUTHORIZATION, jwtStore.getJwt());
				log.info("******** Current JWT ************ " + jwtStore.getJwt());
			}else {
				log.info("******** No JWT ************ ");
			}
			
			request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			
			
			
			ClientHttpResponse response = execution.execute(request, body);
			
			return response;
		};
	}
	
	
}
