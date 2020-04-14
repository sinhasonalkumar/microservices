package com.sonal.demo.istio.userskillsservice.externalservices.restclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.Capability;
import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.CapabilityResponse;

import reactor.core.publisher.Mono;

@Service
public class CapabilityServiceClient {

	@Value("${rest.client.baseurl.capabilityServiceClient}")
	private String capabilityServiceClientBaseURL;
	
	public Mono<List<Capability>> getAllCapabilities() {
		return WebClient.builder()
				 .baseUrl(capabilityServiceClientBaseURL)
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				 .build()
				 .get()
				 .uri("/capabilityService/list")
				 .accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
				 .exchange()
				 .flatMap(r -> r.bodyToMono(CapabilityResponse.class))
				 .map(cr -> cr.getCapabilities());
	}
}
