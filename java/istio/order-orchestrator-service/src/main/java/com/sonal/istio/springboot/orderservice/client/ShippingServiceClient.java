package com.sonal.istio.springboot.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.istio.springboot.orderservice.client.vo.ChainnedResponse;
import com.sonal.istio.springboot.orderservice.client.vo.PaymentResponse;
import com.sonal.istio.springboot.orderservice.client.vo.ProductResponse;
import com.sonal.istio.springboot.orderservice.client.vo.ShippingRequest;
import com.sonal.istio.springboot.orderservice.client.vo.ShippingResponse;
import com.sonal.istio.springboot.orderservice.client.vo.UserProfileResponse;

import reactor.core.publisher.Mono;

@Service
public class ShippingServiceClient {

	@Value("${shippingServiceBaseURL}")
	private String shippingServiceBaseURL;
	
	
	public Mono<ChainnedResponse> ship(UserProfileResponse userProfileResponse, ProductResponse productResponse, PaymentResponse paymentResponse, String productId) {
		return WebClient.builder()
				 .baseUrl(shippingServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build()
				 .post()
				 .uri("/shipping-service/ship/")
				 .body(Mono.just(ShippingRequest.builder()
						 						.productId(productId)
						 						.fullName(userProfileResponse.getFirstName() + userProfileResponse.getLastName())
						 						.address(userProfileResponse.getAddress())
						 						.build()), ShippingRequest.class)
				 .accept(MediaType.APPLICATION_JSON)
				 .exchangeToMono(response -> {
					  if (response.statusCode()
							    .equals(HttpStatus.OK)) {
							      return response.bodyToMono(ShippingResponse.class);
							  } else if (response.statusCode().is4xxClientError()) {
							      return Mono.just(ShippingResponse.builder().build());
							  } else {
							      return response.createException()
							                     .flatMap(Mono::error);
							  }
				}).map(shippingResponse -> ChainnedResponse.builder()
														   .paymentResponse(paymentResponse)
														   .userProfileResponse(userProfileResponse)
														   .productResponse(productResponse)
														   .shippingResponse(shippingResponse)
														   .build());
			
	}
}
