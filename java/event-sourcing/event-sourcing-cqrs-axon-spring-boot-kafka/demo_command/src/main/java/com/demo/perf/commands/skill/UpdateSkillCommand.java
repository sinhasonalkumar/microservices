package com.demo.perf.commands.skill;

import java.util.List;

public class UpdateSkillCommand {
	
	public final String skillId;

	public final String name;

	public final String description;

	public final String capabilityId;

	public final List<UpdateSkillItemCommand> skillItems;

	public UpdateSkillCommand(String skillId, String name, String description, String capabilityId,
			List<UpdateSkillItemCommand> skillItems) {
		this.skillId = skillId;
		this.description = description;
		this.name = name;
		this.capabilityId = capabilityId;
		this.skillItems = skillItems;
	}

}
