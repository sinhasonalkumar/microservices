package com.demo.perf.events.skill;

import java.util.List;

import com.demo.perf.aggregates.skill.SkillItem;

public class SkillUpdatedEvent {

	public final String skillId;

	public final String name;

	public final String description;

	public final String capabilityId;

	public final List<SkillItem> skillItems;

	public SkillUpdatedEvent(String skillId, String name, String description, String capabilityId, List<SkillItem> skillItems) {
		this.skillId = skillId;
		this.description = description;
		this.name = name;
		this.capabilityId = capabilityId;
		this.skillItems = skillItems;
	}

}
