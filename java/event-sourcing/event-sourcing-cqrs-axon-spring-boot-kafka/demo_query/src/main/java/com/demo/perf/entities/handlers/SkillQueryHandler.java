package com.demo.perf.entities.handlers;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.demo.perf.entities.SkillQueryEnitity;
import com.demo.perf.entities.repositories.SkillRepository;
import com.demo.perf.query.skill.FindSkillByIdQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SkillQueryHandler {

	private final SkillRepository skillRepository;

	@QueryHandler
	public SkillQueryEnitity getSkill(FindSkillByIdQuery findSkillByIdQuery) {
		return skillRepository.findById(findSkillByIdQuery.skillId).get();
	}
}
