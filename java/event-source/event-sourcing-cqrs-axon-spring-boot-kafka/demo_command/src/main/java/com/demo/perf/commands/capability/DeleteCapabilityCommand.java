package com.demo.perf.commands.capability;

import com.demo.perf.commands.BaseCommand;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class DeleteCapabilityCommand extends BaseCommand<String> {

    public DeleteCapabilityCommand(String id) {
        super(id);
    }

}
