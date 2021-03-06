package com.sonal.demo.istio.apigateway.externalservice.restclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.Capability;
import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.CapabilityResponse;

import reactor.core.publisher.Mono;

@Service
public class CapabilityServiceClient {

	@Value("${rest.client.baseurl.capabilityService}")
	private String capabilityServiceClientBaseURL;
	
	public Mono<List<Capability>> getAllCapabilities() {
		return WebClient.builder()
				 .baseUrl(capabilityServiceClientBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build()
				 .get()
				 .uri("/capabilityService/list")
				 .accept(MediaType.APPLICATION_JSON)
				 .exchange()
				 .flatMap(r -> r.bodyToMono(CapabilityResponse.class))
				 .map(cr -> cr.getCapabilities());
	}
}
