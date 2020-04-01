package com.demo.perf.events.capability;

import com.demo.perf.aggregates.capability.CapabilityType;
import com.demo.perf.events.BaseEvent;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class CapabilityCreatedEvent extends BaseEvent<String> {

	public final String name;

	public final String description;

	public final CapabilityType capabilityType;

	public CapabilityCreatedEvent(String id, String name, String description, CapabilityType capabilityType) {
		super(id);
		this.description = description;
		this.name = name;
		this.capabilityType = capabilityType;
	}

}
