package com.sonal.demo.istio.capabilityservice.externalservices.restclient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.capabilityservice.externalservices.restclient.dto.Skill;
import com.sonal.demo.istio.capabilityservice.externalservices.restclient.dto.SkillsDiscoveredDTO;
import com.sonal.demo.istio.capabilityservice.service.vo.Capability;

import reactor.core.publisher.Mono;

@Service
public class DiscoverSkillsServiceClient {
	
	@Value("${rest.client.baseurl.skillsDiscoveryService}")
	private String skillsDiscoveryServiceBaseURL ;
	
	public Mono<List<Capability>> discover() {
		
		
		return  WebClient.builder()
				   .baseUrl(skillsDiscoveryServiceBaseURL)
				   .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				   .build()
				   .get()
				   .uri("/discoverSkills/discover")
				   .accept(MediaType.APPLICATION_JSON)
				   .exchange()
				   .flatMap(r -> r.bodyToMono(SkillsDiscoveredDTO.class))
				   .map(ds -> ds.getSkillsDiscovered())
				   .map(s -> mapToCapability(s));
	}
	
	private List<Capability> mapToCapability(List<Skill> skills) {
		
		
		return skills.stream()
					 .map(s -> new Capability(UUID.randomUUID().toString(), s.getId(), s.getSkillName()))
					 .collect(Collectors.toList());
	}

}
