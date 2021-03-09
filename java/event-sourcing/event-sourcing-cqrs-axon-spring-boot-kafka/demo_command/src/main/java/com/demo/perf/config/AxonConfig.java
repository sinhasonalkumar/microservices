package com.demo.perf.config;

import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.perf.aggregates.capability.CapabilityAggregate;
import com.mongodb.MongoClient;

@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AxonConfig {

	@Autowired
	private EventStore myEventStore;

	@Bean
    public EventStorageEngine eventStore(MongoClient client) {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(client));
    }
	@Bean
	public AggregateFactory<CapabilityAggregate> aggregateFactory(){
		return new GenericAggregateFactory<CapabilityAggregate>(CapabilityAggregate.class);
	}
	
	@Bean
	public Snapshotter snapShotter(AggregateFactory<CapabilityAggregate> aggregateFactory){
		return new AggregateSnapshotter(myEventStore, aggregateFactory);
	}
	
	@Bean
	public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
		return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
	}
	@Bean
	public Repository<CapabilityAggregate> accountAggregateRepository(SnapshotTriggerDefinition snapshotTriggerDefinition,AggregateFactory<CapabilityAggregate> aggregateFactory){
		return new EventSourcingRepository<CapabilityAggregate>(aggregateFactory, myEventStore,snapshotTriggerDefinition);
	}
}
