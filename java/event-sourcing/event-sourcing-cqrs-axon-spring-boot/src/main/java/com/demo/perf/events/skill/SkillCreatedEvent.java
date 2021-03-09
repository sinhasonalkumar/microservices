package com.demo.perf.events.skill;

import java.util.List;

import com.demo.perf.aggregates.skill.SkillItem;

public class SkillCreatedEvent  {

	public final String id;
	
	public final String name;

	 public final String description;
	 
	 public final String capabilityId;
   
	 public final List<SkillItem> skillItems;

	 public SkillCreatedEvent(String id, String name, String description, String capabilityId, List<SkillItem> skillItems) {
	        this.id = id;
	        this.description = description;
	        this.name = name;
	        this.capabilityId = capabilityId;
	        this.skillItems = skillItems;
	    }
}