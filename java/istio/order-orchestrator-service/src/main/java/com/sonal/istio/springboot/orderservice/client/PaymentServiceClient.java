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
import com.sonal.istio.springboot.orderservice.client.vo.UserProfileResponse;

import reactor.core.publisher.Mono;

@Service
public class PaymentServiceClient {

	@Value("${paymentServiceBaseURL}")
	private String paymentServiceBaseURL;
	
	
	public Mono<ChainnedResponse> postPayment(UserProfileResponse userProfileResponse , ProductResponse productResponse) {
		return WebClient.builder()
				 .baseUrl(paymentServiceBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build()
				 .post()
				 .uri(uriBuilder -> uriBuilder.path("payment-service/payment/post/{accountId}/{amount}").build(userProfileResponse.getAccountId(),productResponse.getCost()))
				 .accept(MediaType.APPLICATION_JSON)
				 .exchangeToMono(response -> {
					  if (response.statusCode()
							    .equals(HttpStatus.OK)) {
							      return response.bodyToMono(PaymentResponse.class);
							  } else if (response.statusCode().is4xxClientError()) {
							      return Mono.just(PaymentResponse.builder().build());
							  } else {
							      return response.createException()
							                     .flatMap(Mono::error);
							  }
				 }).map(paymentResponse -> ChainnedResponse.builder()
						 								   .paymentResponse(paymentResponse)
						 								   .userProfileResponse(userProfileResponse)
						 								   .productResponse(productResponse)
						 								   .build());
			
	}
}
