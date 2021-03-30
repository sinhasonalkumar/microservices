package com.sonal.istio.springboot.paymentservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.istio.springboot.paymentservice.client.vo.ChainedResponse;
import com.sonal.istio.springboot.paymentservice.client.vo.UserProfileResponse;
import com.sonal.istio.springboot.paymentservice.vo.PaymentResponse;

import reactor.core.publisher.Mono;

@Service
public class ProfileServiceClient {
	
	@Autowired
	private WebClient profileServiceWebClient;
	
	public Mono<ChainedResponse> userProfile(String accountId, PaymentResponse paymentResponse) {
		return profileServiceWebClient
				 .get()
				 .uri(uriBuilder -> uriBuilder.path("/profile-service/profile/user/account/{accountId}").build(accountId))
				 .accept(MediaType.APPLICATION_JSON)
				 .exchangeToMono(response -> {
					  if (response.statusCode()
							    .equals(HttpStatus.OK)) {
							      return response.bodyToMono(UserProfileResponse.class);
							  } else if (response.statusCode().is4xxClientError()) {
							      return Mono.just(UserProfileResponse.builder().build());
							  } else {
							      return response.createException()
							                     .flatMap(Mono::error);
							  }
							}).map(userProfileResponse -> {
								return ChainedResponse.builder()
								 					  .paymentResponse(paymentResponse)
													  .userProfileResponse(userProfileResponse)
								 				      .build();
								 
							 });
			
	}
	
}
