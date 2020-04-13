package com.sonal.demo.istio.apigateway.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.demo.istio.apigateway.service.APIGatewayService;
import com.sonal.demo.istio.apigateway.service.vo.EmployeeDetails;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("api/employeeDetails")
@RestController
public class APIGatewayController {
	
	private APIGatewayService apiGatewayService;
	
	@GetMapping(value = "/{userId}",produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<EmployeeDetails> getUserDeatils(@PathVariable String userId) {
		
		return apiGatewayService.getUserDetails(userId);
	}
}
