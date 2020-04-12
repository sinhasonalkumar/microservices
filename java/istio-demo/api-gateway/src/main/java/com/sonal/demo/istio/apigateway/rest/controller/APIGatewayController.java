package com.sonal.demo.istio.apigateway.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.demo.istio.apigateway.service.APIGatewayService;
import com.sonal.demo.istio.apigateway.service.vo.UserDetails;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("api")
@RestController
public class APIGatewayController {
	
	private APIGatewayService apiGatewayService;
	
	@GetMapping(value = "/userDetails/{userId}")
	public Mono<UserDetails> getUserDeatils(@PathVariable String userId) {
		
		return apiGatewayService.getUserDetails(userId);
	}
}
