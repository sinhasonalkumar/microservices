package com.demo.perf.events.skill;

public class SkillUpdatedEvent {

	public final String skillId;

	public final String name;

	public final String description;

	public final String capabilityId;
	

	public SkillUpdatedEvent(String skillId, String name, String description, String capabilityId) {
		this.skillId = skillId;
		this.description = description;
		this.name = name;
		this.capabilityId = capabilityId;
		
	}

}
