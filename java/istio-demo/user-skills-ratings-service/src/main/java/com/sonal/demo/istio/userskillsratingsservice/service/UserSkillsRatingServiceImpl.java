package com.sonal.demo.istio.userskillsratingsservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sonal.demo.istio.userskillsratingsservice.service.vo.SkillsRating;
import com.sonal.demo.istio.userskillsratingsservice.service.vo.UserSkillsRatings;

import reactor.core.publisher.Mono;

@Service
public class UserSkillsRatingServiceImpl implements UserSkillsRatingService {

	@Override
	public Mono<UserSkillsRatings> getUserSkillsRatings(String userId) {
		
		UserSkillsRatings userSkillsRatings = new UserSkillsRatings();
		
		userSkillsRatings.setUserId(userId);
		
		SkillsRating r1 = new SkillsRating("933f1396-4106-4f93-ae3d-d44ec82f27a7", "9.5");
		SkillsRating r2 = new SkillsRating("a5834ac7-4518-437b-9f86-f2a761bae810", "10");
		SkillsRating r3 = new SkillsRating("65c07554-3503-4d2b-a838-2155b3f25e46", "9.2");
		SkillsRating r4 = new SkillsRating("b9c579af-2610-42b0-9bbb-60a7c3f52935", "9.3");
		SkillsRating r5 = new SkillsRating("899a4b5c-0f16-4d70-aeae-d75dbe140b73", "9.5");
		
		List<SkillsRating> ratings = new ArrayList<>();
		ratings.add(r1);
		ratings.add(r2);
		ratings.add(r3);
		ratings.add(r4);
		ratings.add(r5);
		
		userSkillsRatings.setSkillsRatings(ratings);
		
		return Mono.just(userSkillsRatings);
		
	}
}
