package com.demo.perf.commands.skill;

public class CreateSkillItemCommand {

	public final String skillItemId;
	
	public final String name;

	public final String description;

	public CreateSkillItemCommand(String skillItemId, String name, String description) {
		this.skillItemId = skillItemId;
		this.description = description;
		this.name = name;

	}

}
