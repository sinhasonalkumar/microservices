package com.sonal.kubernetes.demo.kubernetesdemo.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class HelathCheckController {
	
	@GetMapping("/")
	public Mono<String> checkHealth() {
		return Mono.just("healthy");
	}

}
