package com.sonal.demo.istio.userskillsservice.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.demo.istio.userskillsservice.rest.dto.UserSkillsDetailsResponse;
import com.sonal.demo.istio.userskillsservice.service.UserSkillsService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("usersSkills")
@RestController
public class UserSkillsController {

	private UserSkillsService userSkillsService;
	
	@GetMapping(value = "/details/{userId}" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<UserSkillsDetailsResponse> userSkillsDetails(@PathVariable String userId) {
		return userSkillsService.getUserSkillsDetails(userId)
				                .map(UserSkillsDetailsResponse :: new);
								
	}
}
