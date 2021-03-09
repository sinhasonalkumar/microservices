package com.demo.perf.events.skill;

import com.demo.perf.events.BaseEvent;

public class SkillItemCreatedEvent extends BaseEvent<String> {

	public final String name;

	public final String description;

	public SkillItemCreatedEvent(String id, String name, String description) {
		super(id);
		this.description = description;
		this.name = name;

	}
}
