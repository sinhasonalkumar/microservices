package com.demo.perf.events.skill;

import com.demo.perf.events.BaseEvent;

public class SkillDeletedEvent extends BaseEvent<String> {
	
    public SkillDeletedEvent(String id) {
    	super(id);
    }

}
