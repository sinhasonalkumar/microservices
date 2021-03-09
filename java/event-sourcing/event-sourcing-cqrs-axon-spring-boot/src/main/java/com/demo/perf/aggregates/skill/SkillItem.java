package com.demo.perf.aggregates.skill;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.EntityId;

import com.demo.perf.events.skill.SkillItemCreatedEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillItem {

	@EntityId
	private String skillItemId;

	private String name;

	private String description;

	@EventSourcingHandler
	public void on(SkillItemCreatedEvent skillItemCreatedEvent) {
		this.skillItemId = skillItemCreatedEvent.id;
		this.name = skillItemCreatedEvent.name;
		this.description = skillItemCreatedEvent.description;
	}

}
