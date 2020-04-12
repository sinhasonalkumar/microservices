package com.sonal.demo.istio.capabilityservice.service;

import java.util.List;

import com.sonal.demo.istio.capabilityservice.service.vo.Capability;

import reactor.core.publisher.Mono;

public interface CapabilityService {

	Mono<List<Capability>> listAllCapabilities();

}