package com.demo.perf.commands.capability;

import com.demo.perf.aggregates.capability.CapabilityType;
import com.demo.perf.commands.BaseCommand;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class CreateCapabilityCommand extends BaseCommand<String> {

    public final String name;

    public final String description;
    
    public final CapabilityType capabilityType;
    
    public CreateCapabilityCommand(String id, String name, String description, CapabilityType capabilityType) {
        super(id);
        this.description = description;
        this.name = name;
        this.capabilityType = capabilityType;
    }
}
