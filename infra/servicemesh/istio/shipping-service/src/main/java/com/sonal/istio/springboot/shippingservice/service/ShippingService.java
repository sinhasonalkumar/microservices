package com.sonal.istio.springboot.shippingservice.service;

import java.text.MessageFormat;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.shippingservice.vo.ShippingRequest;
import com.sonal.istio.springboot.shippingservice.vo.ShippingResponse;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@AllArgsConstructor
public class ShippingService {
	

	public Mono<ShippingResponse> ship(ShippingRequest shippingRequest) {
		
		String trackingNumber = UUID.randomUUID().toString();
		
		String message = MessageFormat.format("Package({0})  has been shipped to {1} at {2}. Tracking Number is {3}", 
												shippingRequest.getProductId(), 
												shippingRequest.getFullName(), 
												shippingRequest.getAddress(),
												trackingNumber);
		
		
		log.info(message);
		
		return Mono.just(ShippingResponse.builder()
						.trackingNumber(trackingNumber)
						.message(message)
						.carrier("FedEx")
						.build());
								
	}
	
}
