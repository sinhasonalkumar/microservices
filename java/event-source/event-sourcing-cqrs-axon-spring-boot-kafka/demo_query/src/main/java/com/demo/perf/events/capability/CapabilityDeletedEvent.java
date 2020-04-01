package com.demo.perf.events.capability;

import com.demo.perf.events.BaseEvent;

import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = true)
@NoArgsConstructor
public class CapabilityDeletedEvent extends BaseEvent<String> {
	
    public CapabilityDeletedEvent(String id) {
    	super(id);
    }

}
