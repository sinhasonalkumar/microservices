package com.sonal.istio.springboot.paymentservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.istio.springboot.paymentservice.service.PaymentService;
import com.sonal.istio.springboot.paymentservice.vo.PaymentResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {

	
	private PaymentService paymentService;
	
	@PostMapping(value = "/post/{accountId}/{amount}")
	public Mono<ResponseEntity<PaymentResponse>> postPayment(@PathVariable String accountId, @PathVariable Float amount){
	
		
		return paymentService.pay(accountId, amount)
				             .map(paymentResponse -> ResponseEntity.ok(paymentResponse));
		
	}
	
}
