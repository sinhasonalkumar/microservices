package com.sonal.demo.istio.apigateway.service;

import com.sonal.demo.istio.apigateway.service.vo.UserDetails;

import reactor.core.publisher.Mono;

public interface APIGatewayService {

	Mono<UserDetails> getUserDetails(String userId);

}