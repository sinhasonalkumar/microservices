package com.demo.perf.services.queries;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.demo.perf.entities.SkillQueryEnitity;

public interface SkillQueryService {

	List<Object> listEventsForSkill(String skillId);
	CompletableFuture<SkillQueryEnitity> getSkill(String skillId);
}
