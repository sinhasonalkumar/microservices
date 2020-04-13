package com.sonal.demo.istio.apigateway.externalservice.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.UserSkillsDetailsResponse;

import reactor.core.publisher.Mono;

@Service
public class UserSkillsServiceClient {
	
	@Value("${rest.client.baseurl.userSkillsService}")
	private String userSkillsServiceBaseURL;
	
	public Mono<UserSkillsDetailsResponse> getUserSkillsDetails(String userId) {
		
		return WebClient.builder()
						.baseUrl(userSkillsServiceBaseURL)
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build()
						.get()
						.uri("/usersSkills/details/{userId}", userId)
						.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
						.exchange()
						.flatMap(r -> r.bodyToMono(UserSkillsDetailsResponse.class));
	}

}
