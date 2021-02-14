package com.sonal.microservices.profileservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.microservices.profileservice.controller.vo.UserProfileResponseVO;
import com.sonal.microservices.profileservice.services.IUserProfileService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/user/profile")
public class UserProfileController {
	
	@Autowired
	private IUserProfileService userProfileService;

	@GetMapping(value = "/{userId}")
	public Mono<ResponseEntity<UserProfileResponseVO>> profile(@PathVariable("userId") String userId) {
		
		return userProfileService.fetchUserProfile(userId)
								 .map(f -> ResponseEntity.ok(new UserProfileResponseVO(f)));
	}
	
}
