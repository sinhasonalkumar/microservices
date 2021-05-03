package com.sonal.istio.springboot.shippingservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.istio.springboot.shippingservice.service.ShippingService;
import com.sonal.istio.springboot.shippingservice.vo.ShippingRequest;
import com.sonal.istio.springboot.shippingservice.vo.ShippingResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ship")
@AllArgsConstructor
public class ShippingController {

	
	private ShippingService shippingService;
	
	
	@PostMapping(value = "/")
	public Mono<ResponseEntity<ShippingResponse>> shipPackage(@RequestBody ShippingRequest shippingRequest){
	
		
		return shippingService.ship(shippingRequest)
				              .map(shippingResponse -> ResponseEntity.ok(shippingResponse));
		
	}
	
}
