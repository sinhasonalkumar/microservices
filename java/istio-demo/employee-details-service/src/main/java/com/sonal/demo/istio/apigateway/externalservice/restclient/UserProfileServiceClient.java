package com.sonal.demo.istio.apigateway.externalservice.restclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.UserProfileResponse;
import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.UsersProfilesResponse;

import reactor.core.publisher.Mono;

@Service
public class UserProfileServiceClient {

	@Value("${rest.client.baseurl.userProfileService}")
	private String userProfileServiceBaseURL ;
	
	public Mono<UserProfileResponse> getUserProfile(String userId) {
		return WebClient.builder()
						.baseUrl(userProfileServiceBaseURL)
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build()
						.get()
						.uri("/userProfile/{userId}", userId)
						.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
						.exchange()
						.flatMap(r -> r.bodyToMono(UserProfileResponse.class));
	}
	
	public Mono<UsersProfilesResponse> getUsersProfiles() {
		return WebClient.builder()
						.baseUrl(userProfileServiceBaseURL)
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build()
						.get()
						.uri("/userProfile/showAll")
						.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
						.exchange()
						.flatMap(r -> r.bodyToMono(UsersProfilesResponse.class));
	}
}
