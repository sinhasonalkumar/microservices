package com.sonal.demo.istio.discoverskillsservice.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.demo.istio.discoverskillsservice.rest.dto.SkillsDiscoveredDTO;
import com.sonal.demo.istio.discoverskillsservice.service.SkillsDiscoverer;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@RequestMapping("discoverSkills")
@RestController
@AllArgsConstructor
public class DiscoverSkillsController {
	
	private SkillsDiscoverer skillsDiscoverer;
	
	@GetMapping(value = "discover", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<SkillsDiscoveredDTO> discoverSkills() {
		return skillsDiscoverer.discover()
							   .map(SkillsDiscoveredDTO :: new);
	}
}
