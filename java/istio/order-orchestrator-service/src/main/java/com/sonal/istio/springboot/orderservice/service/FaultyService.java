package com.sonal.istio.springboot.orderservice.service;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.orderservice.vo.OrderResponse;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
public class FaultyService {
	
	@Value(value = "${faultCount}")
	private int faultCount;
	
	// Just to inject fault to show case healing process
	private int count = 0;
	
	
	public Mono<ResponseEntity<OrderResponse>> faultyMethod(){
		
		if(count < faultCount) {
			count ++;
			String incidentId = UUID.randomUUID().toString();
			String message = MessageFormat.format("Fatal Error. Please reachout to Customer Support with IncidentId {0}", incidentId);
			
			log.error(message);
			
			return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body((OrderResponse.builder()
                    .message(message)
                    .build())));
			
			
		}else {
			return Mono.just(ResponseEntity.ok(OrderResponse.builder()
										  .message("Success")
										  .build()));
		}
			
	}
	
	public Mono<ResponseEntity<OrderResponse>> fixedFaultyMethod(){
		
		return Mono.just(ResponseEntity.ok(OrderResponse.builder()
				  .message("Success")
				  .build()));
			
	}
	
	
	public Mono<ResponseEntity<OrderResponse>> slowOp() {
		
		return Mono.just(ResponseEntity.ok(OrderResponse.builder()
							  .message("Success")
							  .build())).delayElement(Duration.ofSeconds(5));
	}
	
	
	@Scheduled(fixedDelayString = "${injectFaultDelay}")
	public void reset() {
		count = 0;
	}
}
