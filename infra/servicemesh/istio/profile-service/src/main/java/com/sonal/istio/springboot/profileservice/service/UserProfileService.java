package com.sonal.istio.springboot.profileservice.service;

import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.profileservice.repository.UserProfileRepository;
import com.sonal.istio.springboot.profileservice.vo.UserProfileResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserProfileService {
	
	
	private UserProfileRepository userProfileRepository;

	public Mono<UserProfileResponse> fetchUserProfile(String userId) {
		
		return userProfileRepository.findByUserId(userId)
								.map(userProfile ->  UserProfileResponse.builder()
																	  .firstName(userProfile.getFirstName())
																	  .lastName(userProfile.getLastName())
																	  .accountId(userProfile.getAccountId())
																	  .address(userProfile.getAddress())
																	  .emailId(userProfile.getEmailId())
																	  .build());
								
	}
	
	
	public Mono<UserProfileResponse> fetchUserByAccountId(String accountId) {
		
		return userProfileRepository.findByPaymentAccountId(accountId)
								    .map(userProfile ->  UserProfileResponse.builder()
																			.firstName(userProfile.getFirstName())
																			.lastName(userProfile.getLastName())
																			.accountId(userProfile.getAccountId())
																			.address(userProfile.getAddress())
																			.emailId(userProfile.getEmailId())
																			.build());
								
	}
	
}
