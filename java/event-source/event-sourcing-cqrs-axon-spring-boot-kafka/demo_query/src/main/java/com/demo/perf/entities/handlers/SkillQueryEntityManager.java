package com.demo.perf.entities.handlers;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

import com.demo.perf.entities.SkillQueryEnitity;
import com.demo.perf.entities.repositories.SkillRepository;
import com.demo.perf.events.skill.SkillCreatedEvent;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SkillQueryEntityManager {

	private SkillRepository skillRepository;

	@EventHandler
	public void createCapability(SkillCreatedEvent skillCreatedEvent) throws Exception {

		SkillQueryEnitity skill = new SkillQueryEnitity(skillCreatedEvent.id, skillCreatedEvent.capabilityId,
				skillCreatedEvent.name, skillCreatedEvent.description);

		skillRepository.save(skill);
	}

	

	@ResetHandler
	public void resetTokensAction() {
		skillRepository.deleteAll();
	}
}
