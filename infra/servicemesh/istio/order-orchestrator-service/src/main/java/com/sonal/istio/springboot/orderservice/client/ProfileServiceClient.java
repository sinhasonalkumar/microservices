package com.sonal.istio.springboot.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.istio.springboot.orderservice.client.vo.UserProfileResponse;

import reactor.core.publisher.Mono;

@Service
public class ProfileServiceClient {
	
	@Autowired
	private WebClient profileServiceWebClient;
	
	public Mono<UserProfileResponse> userProfile(String userId) {
		return profileServiceWebClient
				 .get()
				 .uri(uriBuilder -> uriBuilder.path("/profile-service/profile/user/{userId}").build(userId))
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
							});
			
	}
	
}
