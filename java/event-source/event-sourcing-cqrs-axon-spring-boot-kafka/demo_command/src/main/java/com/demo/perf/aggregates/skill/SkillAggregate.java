package com.demo.perf.aggregates.skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import com.demo.perf.commands.skill.CreateSkillCommand;
import com.demo.perf.commands.skill.DeleteSkillCommand;
import com.demo.perf.commands.skill.UpdateSkillCommand;
import com.demo.perf.events.skill.SkillCreatedEvent;
import com.demo.perf.events.skill.SkillDeletedEvent;
import com.demo.perf.events.skill.SkillUpdatedEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate(snapshotTriggerDefinition = "snapshotTriggerDefinition")
@Data
@NoArgsConstructor
public class SkillAggregate implements Serializable {

	private static final long serialVersionUID = 7297359407030239599L;
	
	@AggregateIdentifier
    private String skillId;
	
	private String capabilityId;

    private String name;

    private String description;
    
    @AggregateMember
    private List<SkillItem> skillItems = new ArrayList<SkillItem>();
    

    @CommandHandler
    public SkillAggregate(CreateSkillCommand createSkillCommand) {
    	
    	List<SkillItem> skillItem = createSkillCommand.skillItems.stream().map(si -> {
    		return new SkillItem(si.skillItemId, si.name, si.description);
    	}).collect(Collectors.toList());
    	
    	AggregateLifecycle.apply(new SkillCreatedEvent(createSkillCommand.skillId, createSkillCommand.name, createSkillCommand.description, createSkillCommand.capabilityId , skillItem));
    }
    
    @CommandHandler
    public SkillAggregate(UpdateSkillCommand updateSkillCommand) {
    	
    	List<SkillItem> skillItem = updateSkillCommand.skillItems.stream().map(si -> {
    		return new SkillItem(si.skillItemId, si.name, si.description);
    	}).collect(Collectors.toList());
    	
    	AggregateLifecycle.apply(new SkillUpdatedEvent(updateSkillCommand.skillId, updateSkillCommand.name, updateSkillCommand.description, updateSkillCommand.capabilityId,skillItem));
    }
    
    @CommandHandler
    public SkillAggregate(DeleteSkillCommand updateSkillCommand) {
    	AggregateLifecycle.apply(new SkillDeletedEvent(updateSkillCommand.skillId));
    }
    
    @EventSourcingHandler
    public void on(SkillCreatedEvent skillCreatedEvent) {
    	this.skillId = skillCreatedEvent.id;
    	this.capabilityId = skillCreatedEvent.capabilityId;
    	this.name = skillCreatedEvent.name;
    	this.description = skillCreatedEvent.description;
    	this.skillItems.addAll(skillCreatedEvent.skillItems);
    }
    
    @EventSourcingHandler
    public void on(SkillUpdatedEvent skillUpdatedEvent) {
    	this.skillId = skillUpdatedEvent.skillId;
    	this.capabilityId = skillUpdatedEvent.capabilityId;
    	this.name = skillUpdatedEvent.name;
    	this.description = skillUpdatedEvent.description;
    	this.skillItems.addAll(skillUpdatedEvent.skillItems);
    }
    
    @EventSourcingHandler
    public void on(SkillDeletedEvent skillDeletedEvent) {
    	this.skillId = skillDeletedEvent.id;
    	
    }
}
