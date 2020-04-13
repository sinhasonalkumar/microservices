package com.sonal.demo.istio.userprofileservice.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.demo.istio.userprofileservice.rest.dto.UserProfileResponse;
import com.sonal.demo.istio.userprofileservice.rest.dto.UsersProfilesResponse;
import com.sonal.demo.istio.userprofileservice.service.UserProfileService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("userProfile")
@RestController
public class UserProfileController {
	
	private UserProfileService userProfileService;

	@RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<UserProfileResponse> show(@PathVariable String userId) {
	
		return userProfileService.getUserProfile(userId);
	}
	
	@RequestMapping(value = "/showAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<UsersProfilesResponse> showAll() {
	
		return userProfileService.getAllUserProfiles();
	}
}
