package com.demo.perf.events.capability;

import com.demo.perf.events.BaseEvent;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class CapabilityDeletedEvent extends BaseEvent<String> {
	
    public CapabilityDeletedEvent(String id) {
    	super(id);
    }

}
