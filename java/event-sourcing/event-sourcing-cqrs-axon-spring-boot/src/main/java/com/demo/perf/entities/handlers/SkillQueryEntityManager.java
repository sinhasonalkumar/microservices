package com.demo.perf.entities.handlers;

import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.springframework.stereotype.Component;

import com.demo.perf.aggregates.skill.SkillItem;
import com.demo.perf.entities.SkillItemQueryEnitity;
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
				skillCreatedEvent.name, skillCreatedEvent.description, build(skillCreatedEvent.skillItems));

		skillRepository.save(skill);
	}

	private List<SkillItemQueryEnitity> build(List<SkillItem> skillItems) {

		return skillItems.stream().map(si -> build(si)).collect(Collectors.toList());

	}

	private SkillItemQueryEnitity build(SkillItem si) {
		return new SkillItemQueryEnitity(si.getSkillItemId(), si.getName(), si.getDescription());
	}

	@ResetHandler
	public void resetTokensAction() {
		skillRepository.deleteAll();
	}
}
