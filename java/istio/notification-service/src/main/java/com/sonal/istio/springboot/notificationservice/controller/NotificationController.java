package com.sonal.istio.springboot.notificationservice.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.istio.springboot.notificationservice.service.NotificationService;
import com.sonal.istio.springboot.notificationservice.vo.NotificationRequest;
import com.sonal.istio.springboot.notificationservice.vo.NotificationResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notify")
@AllArgsConstructor
public class NotificationController {

	
	private NotificationService notificationService;
	
	
	@PostMapping(value = "/email")
	public Mono<ResponseEntity<NotificationResponse>> notifyByEmail(@RequestBody NotificationRequest shippingRequest){
	
		
		return notificationService.notify(shippingRequest)
				              .map(shippingResponse -> ResponseEntity.ok(shippingResponse));
		
	}
	
}
