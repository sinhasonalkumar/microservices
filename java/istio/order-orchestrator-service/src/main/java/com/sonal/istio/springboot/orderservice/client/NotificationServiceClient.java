package com.sonal.istio.springboot.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.istio.springboot.orderservice.client.vo.ChainnedResponse;
import com.sonal.istio.springboot.orderservice.client.vo.NotificationRequest;
import com.sonal.istio.springboot.orderservice.client.vo.NotificationResponse;

import reactor.core.publisher.Mono;

@Service
public class NotificationServiceClient {

	
	@Autowired
	private WebClient notificationServiceWebClient;
	
	
	public Mono<ChainnedResponse> sendNotification(ChainnedResponse chainnedResponse) {
		
		return notificationServiceWebClient
				 .post()
				 .uri("/notification-service/notify/email/")
				 .body(Mono.just(NotificationRequest.builder()
						 							.message("Your Order Has been placed successfully. Order Details " + chainnedResponse.getProductResponse())
						                            .email(chainnedResponse.getUserProfileResponse().getEmailId())
						                            .build()), NotificationRequest.class)
				 .accept(MediaType.APPLICATION_JSON)
				 .exchangeToMono(response -> {
					  if (response.statusCode().equals(HttpStatus.OK)) {
							      return response.bodyToMono(NotificationResponse.class);
							  } else if (response.statusCode().is4xxClientError()) {
							      return Mono.just(NotificationResponse.builder().build());
							  } else {
							      return response.createException()
							                     .flatMap(Mono::error);
							  }
							}
				 ).map(notificationResponse -> {
					 chainnedResponse.setNotificationResponse(notificationResponse);
					 return chainnedResponse;
					 
				 });
			
	}
}
