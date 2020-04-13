package com.sonal.demo.istio.userskillsratingsservice.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.demo.istio.userskillsratingsservice.service.UserSkillsRatingService;
import com.sonal.demo.istio.userskillsratingsservice.service.vo.UserSkillsRatings;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("usersSkillsRatings")
@RestController
public class UsersSkillsRatingsController {

	private UserSkillsRatingService ueserSkillsRatingService;
	
	@GetMapping(value = "/ratings/{userId}", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<UserSkillsRatings> ratings(@PathVariable String userId) {
		return ueserSkillsRatingService.getUserSkillsRatings(userId);
	}
}
