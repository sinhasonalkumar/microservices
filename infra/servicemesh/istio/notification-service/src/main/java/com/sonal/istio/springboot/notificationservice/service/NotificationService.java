package com.sonal.istio.springboot.notificationservice.service;


import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.notificationservice.vo.NotificationRequest;
import com.sonal.istio.springboot.notificationservice.vo.NotificationResponse;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@AllArgsConstructor
public class NotificationService {
	

	public Mono<NotificationResponse> notify(NotificationRequest notificationRequest) {
		
		
		String message = MessageFormat.format("Notification has been sent to email {0} with message {1}", 
												notificationRequest.getEmail(), 
												notificationRequest.getMessage());
		
		
		log.info(message);
		
		return Mono.just(NotificationResponse.builder()
						.sent(true)
						.build());
								
	}
	
}
