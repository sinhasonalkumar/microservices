package com.demo.perf.controllers.query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.perf.entities.SkillQueryEnitity;
import com.demo.perf.services.queries.SkillQueryService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/Skill")
@Api(value = "Skill Queries", description = "Skill Query Events Endpoint", tags = "Skill Queries")
@AllArgsConstructor
public class SkillQueryController {

	private final SkillQueryService skillQueryService;

	@GetMapping("/{skillId}")
	public CompletableFuture<SkillQueryEnitity> getCapability(@PathVariable(value = "skillId") String skillId) {
		return skillQueryService.getSkill(skillId);
	}

	@GetMapping("/{skillId}/events")
	public List<Object> listEventsForCapability(@PathVariable(value = "skillId") String skillId) {
		return skillQueryService.listEventsForSkill(skillId);
	}
}
