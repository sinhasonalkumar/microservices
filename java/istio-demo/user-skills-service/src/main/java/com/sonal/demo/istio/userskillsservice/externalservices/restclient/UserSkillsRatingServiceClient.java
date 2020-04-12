package com.sonal.demo.istio.userskillsservice.externalservices.restclient;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.SkillsRating;
import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.UserSkillsRatings;

import reactor.core.publisher.Mono;

@Service
public class UserSkillsRatingServiceClient {

	private String userSkillsRatingsServiceBaseURL = "http://localhost:8083";
	
	public Mono<List<SkillsRating>> getUserSkillsRatings(String userId) {
		return WebClient.builder()
						.baseUrl(userSkillsRatingsServiceBaseURL)
						.build()
						.get()
						.uri("/usersSkillsRatings/ratings/{userId}",userId)
						.accept(MediaType.APPLICATION_JSON)
						.exchange()
						.flatMap(r -> r.bodyToMono(UserSkillsRatings.class))
						.map(usr -> usr.getSkillsRatings());
	}
}
