package com.sonal.demo.istio.userprofileservice.service;

import com.sonal.demo.istio.userprofileservice.rest.dto.UserProfileResponse;
import com.sonal.demo.istio.userprofileservice.rest.dto.UsersProfilesResponse;

import reactor.core.publisher.Mono;

public interface UserProfileService {

	Mono<UserProfileResponse> getUserProfile(String userId);

	Mono<UsersProfilesResponse> getAllUserProfiles();

}