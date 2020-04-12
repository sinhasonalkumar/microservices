package com.sonal.demo.istio.userskillsratingsservice.service;

import com.sonal.demo.istio.userskillsratingsservice.service.vo.UserSkillsRatings;

import reactor.core.publisher.Mono;

public interface UserSkillsRatingService {

	Mono<UserSkillsRatings> getUserSkillsRatings(String userId);

}