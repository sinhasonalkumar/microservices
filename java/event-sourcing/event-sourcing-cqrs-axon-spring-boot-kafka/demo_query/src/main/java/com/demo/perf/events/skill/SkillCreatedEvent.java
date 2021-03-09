package com.demo.perf.events.skill;

public class SkillCreatedEvent  {

	public final String id;
	
	public final String name;

	 public final String description;
	 
	 public final String capabilityId;
   
	 

	 public SkillCreatedEvent(String id, String name, String description, String capabilityId) {
	        this.id = id;
	        this.description = description;
	        this.name = name;
	        this.capabilityId = capabilityId;
	    }
}