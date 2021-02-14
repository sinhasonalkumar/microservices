package com.sonal.microservices.profileservice.services;

import org.springframework.stereotype.Service;

import com.sonal.microservices.profileservice.services.vo.UserProfileVO;

import reactor.core.publisher.Mono;

@Service
public class UserProfileService implements IUserProfileService {
	
	@Override
	public Mono<UserProfileVO>  fetchUserProfile(String userId){
		
		return Mono.just(new UserProfileVO(userId, "Don", "SS "+ userId));
	}
}
