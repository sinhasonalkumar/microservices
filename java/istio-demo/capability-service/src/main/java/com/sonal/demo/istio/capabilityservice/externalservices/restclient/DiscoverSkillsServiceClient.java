package com.sonal.demo.istio.capabilityservice.externalservices.restclient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.sonal.demo.istio.capabilityservice.externalservices.restclient.dto.Skill;
import com.sonal.demo.istio.capabilityservice.externalservices.restclient.dto.SkillsDiscoveredDTO;
import com.sonal.demo.istio.capabilityservice.service.vo.Capability;

import reactor.core.publisher.Mono;

@Service
public class DiscoverSkillsServiceClient {
	
	private  Logger logger = LoggerFactory.getLogger(DiscoverSkillsServiceClient.class);
	
	@Value("${rest.client.baseurl.skillsDiscoveryService}")
	private String skillsDiscoveryServiceBaseURL ;
	
	public Mono<List<Capability>> discover() {
		
		
		return  WebClient.builder()
				   .baseUrl(skillsDiscoveryServiceBaseURL)
				   .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				   .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				   .build()
				   .get()
				   .uri("/discoverSkills/discover")
				   .accept(MediaType.APPLICATION_JSON)
				   .retrieve()
				   .onStatus(HttpStatus::is4xxClientError, response -> {
					   logger.error("4xx eror");
				        return Mono.error(new RuntimeException("4xx"));
				      })
				   .onStatus(HttpStatus::is5xxServerError, response -> {
					   logger.error("5xx eror");
					   logger.error("Response Headers :: " + response.headers());
					   logger.error("Response :: " + response);
				        return Mono.error(new RuntimeException("5xx"));
				      })
				   .bodyToMono(SkillsDiscoveredDTO.class)
				   .doOnError(e -> logger.error(" ********* Error *******",e))
				   .map(ds -> ds.getSkillsDiscovered())
				   .map(s -> mapToCapability(s));
	}
	
	private List<Capability> mapToCapability(List<Skill> skills) {
		
		
		return skills.stream()
					 .map(s -> new Capability(UUID.randomUUID().toString(), s.getId(), s.getSkillName()))
					 .collect(Collectors.toList());
	}

}
