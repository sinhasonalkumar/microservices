package com.demo.perf.events.capability;

import com.demo.perf.entities.CapabilityType;
import com.demo.perf.events.BaseEvent;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = true)
@NoArgsConstructor
public class CapabilityUpdatedEvent extends BaseEvent<String> {

	public  String name;

    public  String description;
    
    public  CapabilityType capabilityType;

    public CapabilityUpdatedEvent(String id, String name, String description,CapabilityType capabilityType) {
    	super(id);
        this.description = description;
        this.name = name;
        this.capabilityType = capabilityType;
    }
}
