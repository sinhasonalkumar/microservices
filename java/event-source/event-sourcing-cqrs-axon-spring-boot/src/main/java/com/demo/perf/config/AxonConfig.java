package com.demo.perf.config;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.perf.aggregates.capability.CapabilityAggregate;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<CapabilityAggregate> CapabilityAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<CapabilityAggregate> repository = EventSourcingRepository.builder(CapabilityAggregate.class).eventStore(eventStore).build();
        return repository;
    }
    
    @Bean
    public SnapshotTriggerDefinition perfSnapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }

}
