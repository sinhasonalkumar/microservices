package com.sonal.istio.springboot.profileservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.istio.springboot.profileservice.service.UserProfileService;
import com.sonal.istio.springboot.profileservice.vo.UserProfileResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
public class ProfileController {

	
	private UserProfileService userProfileService;
	
	
	@GetMapping(value = "/user/{userId}")
	public Mono<ResponseEntity<UserProfileResponse>> postPayment(@PathVariable String userId){
	
		
		return userProfileService.fetchUserProfile(userId)
				             .map(profileResponse -> ResponseEntity.ok(profileResponse));
		
	}
	
}
