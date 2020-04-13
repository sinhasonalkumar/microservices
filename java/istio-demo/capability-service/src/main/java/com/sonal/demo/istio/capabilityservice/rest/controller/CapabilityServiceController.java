package com.sonal.demo.istio.capabilityservice.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.demo.istio.capabilityservice.rest.dto.CapabilityResponseDTO;
import com.sonal.demo.istio.capabilityservice.service.CapabilityService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@RequestMapping("capabilityService")
@RestController
public class CapabilityServiceController {
	
	private CapabilityService capabilityService;
	
	@GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<CapabilityResponseDTO> listAllCapabilities() {
		
		return capabilityService.listAllCapabilities()
								.map(CapabilityResponseDTO :: new);
	}
}
