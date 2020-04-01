package com.demo.perf.commands.skill;

public class UpdateSkillItemCommand {
	
	public final String skillItemId;

	public final String name;

	public final String description;

	public UpdateSkillItemCommand(String skillItemId, String name, String description) {
		this.skillItemId = skillItemId;
		this.description = description;
		this.name = name;

	}

}
