package com.demo.perf.aggregates.capability;
import java.io.Serializable;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.perf.commands.capability.CreateCapabilityCommand;
import com.demo.perf.commands.capability.DeleteCapabilityCommand;
import com.demo.perf.commands.capability.UpdateCapabilityCommand;
import com.demo.perf.events.capability.CapabilityCreatedEvent;
import com.demo.perf.events.capability.CapabilityDeletedEvent;
import com.demo.perf.events.capability.CapabilityUpdatedEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate(snapshotTriggerDefinition = "snapshotTriggerDefinition")
@Data
@NoArgsConstructor
public class CapabilityAggregate implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5371008864652016669L;
	
	private Logger logger = LoggerFactory.getLogger(CapabilityAggregate.class);
	
	@Autowired
	private EventSourcingRepository<CapabilityAggregate> repo;

	@AggregateIdentifier
    private String id;

    private String name;

    private String description;
    
    private CapabilityType capabilityType;

    @CommandHandler
    public CapabilityAggregate(CreateCapabilityCommand createCapabilityCommand){
    	logger.info(" @CommandHandler :: CreateCapabilityCommand " + createCapabilityCommand);
    	AggregateLifecycle.apply(new CapabilityCreatedEvent(createCapabilityCommand.id, createCapabilityCommand.name, createCapabilityCommand.description, createCapabilityCommand.capabilityType));
    }
    
    @CommandHandler
    public void on(UpdateCapabilityCommand updateCapabilityCommand){
    	logger.info("  @CommandHandler :: UpdateCapabilityCommand : " + updateCapabilityCommand);
    	AggregateLifecycle.apply(new CapabilityUpdatedEvent(updateCapabilityCommand.id, updateCapabilityCommand.name, updateCapabilityCommand.description , updateCapabilityCommand.capabilityType));
    }
    
    @CommandHandler
    public void on(DeleteCapabilityCommand deleteCapabilityCommand){
    	logger.info(" @CommandHandler :: DeleteCapabilityCommand : " + deleteCapabilityCommand);
    	AggregateLifecycle.apply(new CapabilityDeletedEvent(deleteCapabilityCommand.id));
    }
    

    @EventSourcingHandler
    protected void on(CapabilityCreatedEvent capabilityCreatedEvent){
    	logger.info("@EventSourcingHandler :: CapabilityCreatedEvent : " + capabilityCreatedEvent);
    	this.id = capabilityCreatedEvent.id;
        this.name = capabilityCreatedEvent.name;
        this.description = capabilityCreatedEvent.description;
    }
    
    @EventSourcingHandler
    protected void on(CapabilityUpdatedEvent capabilityUpdatedEvent){
    	logger.info("@EventSourcingHandler :: CapabilityUpdatedEvent : " + capabilityUpdatedEvent);
    	this.id = capabilityUpdatedEvent.id;
        this.name = capabilityUpdatedEvent.name;
        this.description = capabilityUpdatedEvent.description;
    }
    
    @EventSourcingHandler
    protected void on(CapabilityDeletedEvent capabilityUpdatedEvent){
    	logger.info("@EventSourcingHandler :: CapabilityDeletedEvent : " + capabilityUpdatedEvent);
    	this.id = capabilityUpdatedEvent.id;
    }
    

    
    
}
