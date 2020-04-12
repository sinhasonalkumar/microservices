package com.sonal.demo.istio.userskillsservice.externalservices.restclient;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.Capability;
import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.CapabilityResponse;

import reactor.core.publisher.Mono;

@Service
public class CapabilityServiceClient {

	private String capabilityServiceClientBaseURL = "http://localhost:8084/";
	
	public Mono<List<Capability>> getAllCapabilities() {
		return WebClient.builder()
				 .baseUrl(capabilityServiceClientBaseURL)
				 .build()
				 .get()
				 .uri("/capabilityService/list")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .flatMap(r -> r.bodyToMono(CapabilityResponse.class))
				 .map(cr -> cr.getCapabilities());
	}
}
