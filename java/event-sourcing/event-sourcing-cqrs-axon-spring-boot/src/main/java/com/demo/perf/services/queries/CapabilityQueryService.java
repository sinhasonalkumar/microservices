package com.demo.perf.services.queries;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.demo.perf.entities.CapabilityQueryEntity;

import reactor.core.publisher.Flux;

public interface CapabilityQueryService {
	Flux<CapabilityQueryEntity> listAllCapabilities();
	List<Object> listEventsForCapability(String capabilityId);
    CompletableFuture<CapabilityQueryEntity> getCapability(String capabilityId);
	void replayEvents(String name);
}
