package com.sonal.demo.istio.apigateway.service;

import com.sonal.demo.istio.apigateway.service.vo.EmployeeDetails;

import reactor.core.publisher.Mono;

public interface APIGatewayService {

	Mono<EmployeeDetails> getUserDetails(String userId);

}