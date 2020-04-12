package com.sonal.demo.istio.capabilityservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sonal.demo.istio.capabilityservice.externalservices.restclient.DiscoverSkillsServiceClient;
import com.sonal.demo.istio.capabilityservice.service.vo.Capability;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CapabilityServiceImpl implements CapabilityService {
	
	private DiscoverSkillsServiceClient discoverSkillsServiceClient;
	
	@Override
	public Mono<List<Capability>> listAllCapabilities() {
		
		Capability capability1 = new Capability("933f1396-4106-4f93-ae3d-d44ec82f27a7", null, "Java14");
		Capability capability2 = new Capability("a5834ac7-4518-437b-9f86-f2a761bae810", null, "Spring-Boot");
		Capability capability3 = new Capability("65c07554-3503-4d2b-a838-2155b3f25e46", null, "AWS");
		Capability capability4 = new Capability("b9c579af-2610-42b0-9bbb-60a7c3f52935", null, "Docker");
		Capability capability5 = new Capability("899a4b5c-0f16-4d70-aeae-d75dbe140b73", null, "Kubernetes");
		
		List<Capability> capabilities = new ArrayList<>();
		
		capabilities.add(capability1);
		capabilities.add(capability2);
		capabilities.add(capability3);
		capabilities.add(capability4);
		capabilities.add(capability5);
		
		return discoverSkillsServiceClient.discover()
				   					      .map(c -> aggregateCapabilities(c,capabilities));
		
	}
	
	private List<Capability> aggregateCapabilities(List<Capability> capabilties,  List<Capability> discoveredCapabilities) {
		
		List<Capability> aggregateCapabilities = new ArrayList<>();
		aggregateCapabilities.addAll(capabilties);
		aggregateCapabilities.addAll(discoveredCapabilities);
		
		return aggregateCapabilities;
	}
	
	
}
