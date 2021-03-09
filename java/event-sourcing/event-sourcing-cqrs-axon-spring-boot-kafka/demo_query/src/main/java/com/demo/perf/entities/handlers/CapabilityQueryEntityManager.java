package com.demo.perf.entities.handlers;

import java.time.Instant;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.eventhandling.Timestamp;
import org.springframework.stereotype.Component;

import com.demo.perf.entities.CapabilityQueryEntity;
import com.demo.perf.entities.repositories.CapabilityRepository;
import com.demo.perf.events.capability.CapabilityCreatedEvent;
import com.demo.perf.events.capability.CapabilityDeletedEvent;
import com.demo.perf.events.capability.CapabilityUpdatedEvent;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@ProcessingGroup("CapabilityProcessingGroup")
public class CapabilityQueryEntityManager {
	
	//private Logger logger = LoggerFactory.getLogger(CapabilityQueryEntityManager.class);
    
    private CapabilityRepository capabilityRepository;
    
    @EventHandler
	public void createCapability(CapabilityCreatedEvent capabilityCreatedEvent,@Timestamp Instant instant) throws Exception {
    	CapabilityQueryEntity capabilityQueryEntity = new CapabilityQueryEntity(capabilityCreatedEvent.id,capabilityCreatedEvent.name,capabilityCreatedEvent.description,capabilityCreatedEvent.capabilityType);
    	//logger.info(" CapabilityQueryEntityManager :: CapabilityCreatedEvent :: CapabilityQueryEntity :: " + capabilityQueryEntity);  
    	capabilityRepository.save(capabilityQueryEntity);
	}
    
    @EventHandler
	public void updateCapability(CapabilityUpdatedEvent capabilityUpdatedEvent) throws Exception {
       	 CapabilityQueryEntity capabilityQueryEntity = new CapabilityQueryEntity(capabilityUpdatedEvent.id,capabilityUpdatedEvent.name,capabilityUpdatedEvent.description, capabilityUpdatedEvent.capabilityType);
       //	logger.info(" CapabilityQueryEntityManager :: CapabilityUpdatedEvent :: CapabilityQueryEntity :: " + capabilityQueryEntity);
       	 capabilityRepository.save(capabilityQueryEntity);
   	}
    
    @EventHandler
   	public void deleteCapability(CapabilityDeletedEvent capabilityDeletedEvent) throws Exception {
       	 CapabilityQueryEntity capabilityQueryEntity = new CapabilityQueryEntity(capabilityDeletedEvent.id);
     	//logger.info(" CapabilityQueryEntityManager :: CapabilityDeletedEvent :: CapabilityQueryEntity :: " + capabilityQueryEntity);
       	 capabilityRepository.delete(capabilityQueryEntity);
   	}
    
    @ResetHandler
    public void resetTokensAction() {
    	capabilityRepository.deleteAll();
    }
    
}
