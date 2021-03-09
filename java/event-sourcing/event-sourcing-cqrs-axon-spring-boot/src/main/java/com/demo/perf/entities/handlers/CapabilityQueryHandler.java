package com.demo.perf.entities.handlers;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.demo.perf.entities.CapabilityQueryEntity;
import com.demo.perf.entities.repositories.CapabilityRepository;
import com.demo.perf.query.capability.FindCapabilityByIdQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CapabilityQueryHandler {

	private CapabilityRepository capabilityRepository;

	@QueryHandler
	public CapabilityQueryEntity getCapability(FindCapabilityByIdQuery findCapabilityByIdQuery) {
		return capabilityRepository.findById(findCapabilityByIdQuery.capabilityId).get();
	}
	
	@QueryHandler(queryName = "listAllCapability")
    public Iterable<CapabilityQueryEntity> listAllCapability() {
        return capabilityRepository.findAll();
    }
}
