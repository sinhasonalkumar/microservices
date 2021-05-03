package com.sonal.istio.springboot.paymentservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@Configuration
public class RestClientConfig {

	
	@Value("${notificationServiceBaseURL}")
	private String notificationServiceBaseURL;
	
	@Value("${profileServiceBaseURL}")
	private String profileServiceBaseURL;
	
		
	@Bean
	public WebClient notificationServiceWebClient() {
		return WebClient.builder()
				 .baseUrl(notificationServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 //.filter(logRequest())
				 .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
						 												   .wiretap(true)))
				 .build();
	}
	
	@Bean
	public WebClient profileServiceWebClient() {
		
		return WebClient.builder()
				 .baseUrl(profileServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build();
	}
	
	

}
