package com.sonal.demo.istio.userskillsservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sonal.demo.istio.userskillsservice.externalservices.restclient.CapabilityServiceClient;
import com.sonal.demo.istio.userskillsservice.externalservices.restclient.UserSkillsRatingServiceClient;
import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.Capability;
import com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto.SkillsRating;
import com.sonal.demo.istio.userskillsservice.service.vo.UserSkill;
import com.sonal.demo.istio.userskillsservice.service.vo.UserSkillsDetails;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UserSkillsServiceImpl implements UserSkillsService {
	
	private CapabilityServiceClient capabilityServiceClient;
	
	private UserSkillsRatingServiceClient userSkillsRatingServiceClient;

	@Override
	public Mono<List<UserSkill>> getUserSkills(String userId) {
		UserSkill sk1 = new UserSkill("933f1396-4106-4f93-ae3d-d44ec82f27a7");
		UserSkill sk2 = new UserSkill("a5834ac7-4518-437b-9f86-f2a761bae810");
		UserSkill sk3 = new UserSkill("65c07554-3503-4d2b-a838-2155b3f25e46");
		UserSkill sk4 = new UserSkill("b9c579af-2610-42b0-9bbb-60a7c3f52935");
		UserSkill sk5 = new UserSkill("899a4b5c-0f16-4d70-aeae-d75dbe140b73");
		
		List<UserSkill> userSkills = new ArrayList<UserSkill>();
		userSkills.add(sk1);
		userSkills.add(sk2);
		userSkills.add(sk3);
		userSkills.add(sk4);
		userSkills.add(sk5);
		return Mono.just(userSkills);
	}
	
	@Override
	public Mono<List<UserSkillsDetails>> getUserSkillsDetails(String userId) {
		
		Mono<List<Capability>> allCapabilities = capabilityServiceClient.getAllCapabilities();
		
		Mono<List<UserSkill>> userSkills = getUserSkills(userId);
		
		Mono<List<SkillsRating>> userSkillsRatings = userSkillsRatingServiceClient.getUserSkillsRatings(userId);
		
		return Mono.zip(allCapabilities, userSkills, userSkillsRatings)
				   .map(zr -> buildUserSkillsDetails(zr.getT1(), zr.getT2(), zr.getT3()));
	}
	
	private List<UserSkillsDetails> buildUserSkillsDetails(List<Capability> capabilities, List<UserSkill> userSkills, List<SkillsRating> userSkillsRatings) {
		
		
		Map<String, String> skillsMap = capabilities.stream()
					.collect(Collectors.toMap(c -> c.getCapabilityId(), c -> c.getSkillName()));
		
		
		Map<String, String> skillRatingMap = userSkillsRatings.stream()
					     .collect(Collectors.toMap(usr -> usr.getCapabilityId(), usr -> usr.getRating()));
		
		
		return userSkills.stream()
				  .map(s -> new UserSkillsDetails(s.getCapabilityId(), skillsMap.get(s.getCapabilityId()), skillRatingMap.get(s.getCapabilityId())))
				  .collect(Collectors.toList());
		
	}
}
