package com.sonal.istio.springboot.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.istio.springboot.orderservice.client.vo.ProductResponse;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceClient {

	@Autowired
	private WebClient productServiceWebClient;
	
	public Mono<ProductResponse> product(String productId) {
		
		return productServiceWebClient
				 .get()
				 .uri(uriBuilder -> uriBuilder.path("/product-service/product/{productId}").build(productId))
				 .accept(MediaType.APPLICATION_JSON)
				 .exchangeToMono(response -> {
					  if (response.statusCode()
							    .equals(HttpStatus.OK)) {
							      return response.bodyToMono(ProductResponse.class);
							  } else if (response.statusCode().is4xxClientError()) {
							      return Mono.just(ProductResponse.builder().build());
							  } else {
							      return response.createException()
							                     .flatMap(Mono::error);
							  }
							});
			
	}
	
}
