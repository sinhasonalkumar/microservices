package com.demo.perf.entities.handlers;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import com.demo.perf.entities.CapabilityQueryEntity;
import com.demo.perf.entities.repositories.CapabilityRepository;
import com.demo.perf.events.capability.CapabilityCreatedEvent;
import com.demo.perf.events.capability.CapabilityDeletedEvent;
import com.demo.perf.events.capability.CapabilityUpdatedEvent;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CapabilityQueryEntityManager {
	
    
    private CapabilityRepository capabilityRepository;
    
    private final QueryUpdateEmitter updateEmitter;
    
    @EventHandler
	public void createCapability(CapabilityCreatedEvent capabilityCreatedEvent) throws Exception {
    	CapabilityQueryEntity capabilityQueryEntity = new CapabilityQueryEntity(capabilityCreatedEvent.id,capabilityCreatedEvent.name,capabilityCreatedEvent.description,capabilityCreatedEvent.capabilityType);
    	  
    	capabilityRepository.save(capabilityQueryEntity);
    	updateEmitter.emit(m -> "listAllCapability".equals(m.getQueryName()), capabilityQueryEntity);
	}
    
    @EventHandler
	public void updateCapability(CapabilityUpdatedEvent capabilityUpdatedEvent) throws Exception {
       	 CapabilityQueryEntity capabilityQueryEntity = new CapabilityQueryEntity(capabilityUpdatedEvent.id,capabilityUpdatedEvent.name,capabilityUpdatedEvent.description, capabilityUpdatedEvent.capabilityType);
       
       	 capabilityRepository.save(capabilityQueryEntity);
       	 updateEmitter.emit(m -> "listAllCapability".equals(m.getQueryName()), capabilityQueryEntity);
   	}
    
    @EventHandler
   	public void deleteCapability(CapabilityDeletedEvent capabilityDeletedEvent) throws Exception {
       	 CapabilityQueryEntity capabilityQueryEntity = new CapabilityQueryEntity(capabilityDeletedEvent.id);
     	
       	 capabilityRepository.delete(capabilityQueryEntity);
       	 updateEmitter.emit(m -> "listAllCapability".equals(m.getQueryName()), capabilityQueryEntity);
   	}
    
    @ResetHandler
    public void resetTokensAction() {
    	capabilityRepository.deleteAll();
    }
    
}
