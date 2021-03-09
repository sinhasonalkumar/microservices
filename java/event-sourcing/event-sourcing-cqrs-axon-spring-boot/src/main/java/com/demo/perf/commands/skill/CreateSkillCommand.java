package com.demo.perf.commands.skill;

import java.util.List;

public class CreateSkillCommand {

	public final String skillId;
	
	public final String name;

	 public final String description;
	 
	 public final String capabilityId;
    
	 public final List<CreateSkillItemCommand> skillItems;

	 public CreateSkillCommand(String skillId, String name, String description, String capabilityId , List<CreateSkillItemCommand> skillItems) {
	        this.skillId = skillId;
	        this.description = description;
	        this.name = name;
	        this.capabilityId = capabilityId;
	        this.skillItems = skillItems;
	    }
}


