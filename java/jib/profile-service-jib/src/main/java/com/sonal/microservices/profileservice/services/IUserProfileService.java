package com.sonal.microservices.profileservice.services;

import com.sonal.microservices.profileservice.services.vo.UserProfileVO;

import reactor.core.publisher.Mono;

/*
 * IUserProfileService Manages User Profile.
 */

public interface IUserProfileService {

	Mono<UserProfileVO> fetchUserProfile(String userId);

}