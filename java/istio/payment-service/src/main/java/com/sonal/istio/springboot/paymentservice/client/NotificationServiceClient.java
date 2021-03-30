package com.sonal.istio.springboot.paymentservice.client;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.istio.springboot.paymentservice.client.vo.ChainedResponse;
import com.sonal.istio.springboot.paymentservice.client.vo.NotificationRequest;
import com.sonal.istio.springboot.paymentservice.client.vo.NotificationResponse;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class NotificationServiceClient {

	
	@Autowired
	private WebClient notificationServiceWebClient;
	
	
	public Mono<ChainedResponse> sendNotification(NotificationRequest notificationRequest, ChainedResponse chainedResponse) {
		
		return notificationServiceWebClient
				 .post()
				 .uri("/notification-service/notify/email/")
				 .body(Mono.just(notificationRequest), NotificationRequest.class)
				 .accept(MediaType.APPLICATION_JSON)
				 .exchangeToMono(response -> {
					  if (response.statusCode().equals(HttpStatus.OK)) {
						 return response.bodyToMono(NotificationResponse.class);
							  
					  } else if (response.statusCode().is4xxClientError()) {
								
								  String message = "Response from notification-service " + response.rawStatusCode() + " :: notificationRequest : " + notificationRequest ;
								log.error(message);
							    return Mono.just(NotificationResponse.builder().build());
							    
							  } else {
							      return response.createException()
							                     .flatMap(Mono::error);
							  }
							}
				 ).map(userProfileResponse -> {
					 chainedResponse.setNotificationResponse(userProfileResponse);;
					 return chainedResponse;
					 
				 });
			
	}
}
