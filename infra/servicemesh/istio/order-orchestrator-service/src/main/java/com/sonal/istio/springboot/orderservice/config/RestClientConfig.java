package com.sonal.istio.springboot.orderservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

@EnableScheduling
@Configuration
public class RestClientConfig {

	
	@Value("${notificationServiceBaseURL}")
	private String notificationServiceBaseURL;
	
	@Value("${paymentServiceBaseURL}")
	private String paymentServiceBaseURL;
	
	@Value("${productServiceBaseURL}")
	private String productServiceBaseURL;
	

	@Value("${profileServiceBaseURL}")
	private String profileServiceBaseURL;
	
	@Value("${shippingServiceBaseURL}")
	private String shippingServiceBaseURL;
	
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
	public WebClient paymentServiceWebClient() {
		return WebClient.builder()
				 .baseUrl(paymentServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build();
	}
	
	@Bean
	public WebClient productServiceWebClient() {
		
		return WebClient.builder()
				 .baseUrl(productServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build();
	}
	
	@Bean
	public WebClient profileServiceWebClient() {
		
		return WebClient.builder()
				 .baseUrl(profileServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build();
	}
	
	@Bean
	public WebClient shippingServiceWebClient() {
		return WebClient.builder()
				 .baseUrl(shippingServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build();
	}

}
