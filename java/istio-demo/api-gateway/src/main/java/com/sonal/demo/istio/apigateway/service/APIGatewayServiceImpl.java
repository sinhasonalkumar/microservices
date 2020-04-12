package com.sonal.demo.istio.apigateway.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sonal.demo.istio.apigateway.externalservice.restclient.CapabilityServiceClient;
import com.sonal.demo.istio.apigateway.externalservice.restclient.UserProfileServiceClient;
import com.sonal.demo.istio.apigateway.externalservice.restclient.UserSkillsServiceClient;
import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.Capability;
import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.UserProfileResponse;
import com.sonal.demo.istio.apigateway.externalservice.restclient.dto.UserSkillsDetailsResponse;
import com.sonal.demo.istio.apigateway.service.vo.UserDetails;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class APIGatewayServiceImpl implements APIGatewayService {

	private CapabilityServiceClient capabilityServiceClient;
	
	private UserProfileServiceClient userProfileServiceClient;
	
	private UserSkillsServiceClient skillsServiceClient;
	
	
	@Override
	public Mono<UserDetails> getUserDetails(String userId) {
		
		Mono<List<Capability>> allCapabilities = capabilityServiceClient.getAllCapabilities();
		
		Mono<UserProfileResponse> userProfile = userProfileServiceClient.getUserProfile(userId);
		
		Mono<UserSkillsDetailsResponse> userSkillsDetails = skillsServiceClient.getUserSkillsDetails(userId);
		
		return Mono.zip(allCapabilities, userProfile, userSkillsDetails)
				   .map(t -> buildUserDetails(t.getT1(), t.getT2(), t.getT3()));
		
	}
	
	private UserDetails buildUserDetails(List<Capability> capabilitiesBank, UserProfileResponse userProfileResponse, UserSkillsDetailsResponse userSkillsDetailsResponse ) {
		
		
		UserDetails userDetails = new UserDetails();
		
		userDetails.setUserId(userProfileResponse.getUserId());
		userDetails.setFName(userProfileResponse.getFirstName());
		userDetails.setLName(userProfileResponse.getLastName());
		userDetails.setUserSkillsDetails(userSkillsDetailsResponse.getUserSkillsDetails());
		userDetails.setCapabilitiesBank(capabilitiesBank);
		
		return userDetails;
	}
}
