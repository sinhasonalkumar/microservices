package com.sonal.demo.istio.apigateway.externalservice.restclient;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.UserSkillsDetailsResponse;

import reactor.core.publisher.Mono;

@Service
public class UserSkillsServiceClient {
	
	private String userSkillsServiceBaseURL = "http://localhost:8082/";
	
	public Mono<UserSkillsDetailsResponse> getUserSkillsDetails(String userId) {
		
		return WebClient.builder()
						.baseUrl(userSkillsServiceBaseURL)
						.build()
						.get()
						.uri("/usersSkills/details/{userId}", userId)
						.accept(MediaType.APPLICATION_JSON)
						.exchange()
						.flatMap(r -> r.bodyToMono(UserSkillsDetailsResponse.class));
	}

}
