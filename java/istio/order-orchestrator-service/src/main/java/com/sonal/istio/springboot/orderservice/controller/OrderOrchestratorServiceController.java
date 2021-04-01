package com.sonal.istio.springboot.orderservice.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.istio.springboot.orderservice.service.FaultyService;
import com.sonal.istio.springboot.orderservice.service.OrderOrchestratorService;
import com.sonal.istio.springboot.orderservice.vo.OrderRequest;
import com.sonal.istio.springboot.orderservice.vo.OrderResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderOrchestratorServiceController {

	
	private OrderOrchestratorService orderOrchestratorService;
	
	private FaultyService faultyService;
	
	
	@PostMapping(value = "/place")
	public Mono<ResponseEntity<OrderResponse>> notifyByEmail(@RequestBody OrderRequest orderRequest){
	
		
		return orderOrchestratorService.placeOrder(orderRequest)
				                       .map(shippingResponse -> ResponseEntity.ok(shippingResponse));
		
	}
	
	@GetMapping(value = "/dockerBuildVersion")
	public Mono<ResponseEntity<String>> dockerBuildVersion(){
		
		return Mono.just(ResponseEntity.ok("vFaulty"));
	}
	
	
	@GetMapping(value = "/faulty")
	public Mono<ResponseEntity<OrderResponse>> faulty() {
		
		
		
		return faultyService.faultyMethod();
							
					 
	}
	
}
