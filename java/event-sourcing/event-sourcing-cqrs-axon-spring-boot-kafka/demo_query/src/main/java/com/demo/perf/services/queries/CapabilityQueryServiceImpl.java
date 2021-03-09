package com.demo.perf.services.queries;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import com.demo.perf.entities.CapabilityQueryEntity;
import com.demo.perf.query.capability.FindCapabilityByIdQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CapabilityQueryServiceImpl implements CapabilityQueryService {

	private final EventStore eventStore;

	private final QueryGateway queryGateway;
	
	private final EventProcessingConfiguration eventProcessingConfiguration;

	@Override
	public List<Object> listEventsForCapability(String CapabilityId) {
		return eventStore.readEvents(CapabilityId).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}

	@Override
	public CompletableFuture<CapabilityQueryEntity> getCapability(String capabilityId) {
		return queryGateway.query(new FindCapabilityByIdQuery(capabilityId), CapabilityQueryEntity.class);
	}

	@Override
	public void replayEvents(String name) {
		eventProcessingConfiguration.eventProcessor(name, TrackingEventProcessor.class).ifPresent(processor -> {
			processor.shutDown();
			processor.resetTokens();
			processor.start();
		});
	}

}
