package com.sonal.demo.istio.userskillsservice.externalservices.restclient;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.SkillsRating;
import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.UserSkillsRatings;

import reactor.core.publisher.Mono;

@Service
public class UserSkillsRatingServiceClient {

	@Value("${rest.client.baseurl.userSkillsRatingsService}")
	private String userSkillsRatingsServiceBaseURL;
	
	public Mono<List<SkillsRating>> getUserSkillsRatings(String userId) {
		return WebClient.builder()
						.baseUrl(userSkillsRatingsServiceBaseURL)
						.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
						.build()
						.get()
						.uri("/usersSkillsRatings/ratings/{userId}",userId)
						.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
						.exchange()
						.flatMap(r -> r.bodyToMono(UserSkillsRatings.class))
						.map(usr -> usr.getSkillsRatings());
	}
}
