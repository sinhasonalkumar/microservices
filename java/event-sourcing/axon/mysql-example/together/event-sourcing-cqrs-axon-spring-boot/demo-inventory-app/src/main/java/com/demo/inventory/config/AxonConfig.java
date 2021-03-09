package com.demo.inventory.config;

import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.inventory.command.aggregates.product.ProductAggregate;

@Configuration
public class AxonConfig {

    @Bean
    EventSourcingRepository<ProductAggregate> ProductAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<ProductAggregate> repository = EventSourcingRepository.builder(ProductAggregate.class)
        																			  .eventStore(eventStore)
        																			  .build();
        return repository;
    }
    
    @Bean
    public SnapshotTriggerDefinition inventorySnapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }

}
