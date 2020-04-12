package com.sonal.demo.istio.userprofileservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sonal.demo.istio.userprofileservice.rest.dto.UserProfileResponse;
import com.sonal.demo.istio.userprofileservice.rest.dto.UsersProfilesResponse;

import reactor.core.publisher.Mono;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	@Override
	public Mono<UserProfileResponse> getUserProfile(String userId) {
		
		UserProfileResponse userProfileResponse = new UserProfileResponse();
		userProfileResponse.setUserId("cca0e896-b642-48f3-9c88-6b2bab569109");
		userProfileResponse.setFirstName("Brett");
		userProfileResponse.setLastName("Lee");
		
		return Mono.just(userProfileResponse);
	}
	
	@Override
	public Mono<UsersProfilesResponse> getAllUserProfiles() {
		
		UserProfileResponse userProfileResponse = new UserProfileResponse();
		userProfileResponse.setUserId("cca0e896-b642-48f3-9c88-6b2bab569109");
		userProfileResponse.setFirstName("Brett");
		userProfileResponse.setLastName("Lee");
		
		List<UserProfileResponse> userProfiles = new ArrayList<>();
		userProfiles.add(userProfileResponse);
		
		UsersProfilesResponse usersProfilesResponse = new UsersProfilesResponse();
		usersProfilesResponse.setUsersprofiles(userProfiles);
		
		return Mono.just(usersProfilesResponse);
	}
}
