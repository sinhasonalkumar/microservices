package com.sonal.distributedtracing.orderservice.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.sonal.distributedtracing.orderservice.config.interceptor.RestRequestAndResponseLoggingInterceptor;

@Configuration
public class RestClientCofig {

	@Bean
	public RestTemplate restTemplate() {
		
		ClientHttpRequestFactory clientHttpRequestFactory = new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory());
		
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		
		if (CollectionUtils.isEmpty(interceptors)) {
		    interceptors = new ArrayList<>();
		}
		interceptors.add(new RestRequestAndResponseLoggingInterceptor());
		restTemplate.setInterceptors(interceptors);
		
		return restTemplate;
	}
	
}
