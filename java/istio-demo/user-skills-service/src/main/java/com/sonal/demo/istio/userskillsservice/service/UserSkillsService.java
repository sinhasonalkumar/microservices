package com.sonal.demo.istio.userskillsservice.service;

import java.util.List;

import com.sonal.demo.istio.userskillsservice.service.vo.UserSkill;
import com.sonal.demo.istio.userskillsservice.service.vo.UserSkillsDetails;

import reactor.core.publisher.Mono;

public interface UserSkillsService {

	Mono<List<UserSkill>> getUserSkills(String userId);

	Mono<List<UserSkillsDetails>> getUserSkillsDetails(String userId);

}