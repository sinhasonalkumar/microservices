package com.sonal.demo.istio.discoverskillsservice.service;

import java.util.List;

import com.sonal.demo.istio.discoverskillsservice.service.vo.Skill;

import reactor.core.publisher.Mono;

public interface SkillsDiscoverer {

	Mono<List<Skill>> discover();

}