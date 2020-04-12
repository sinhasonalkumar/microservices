package com.sonal.demo.istio.discoverskillsservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sonal.demo.istio.discoverskillsservice.service.vo.Skill;

import reactor.core.publisher.Mono;

@Service
public class SkillsDiscovererImpl implements SkillsDiscoverer {

	@Override
	public Mono<List<Skill>> discover() {
		
		List<Skill> skills = new ArrayList<Skill>();
		
		Skill skill1 = new Skill("SD-1", "Micronaut");
		Skill skill2 = new Skill("SD-2", "Spring-Web-Flux");
		Skill skill3 = new Skill("SD-3", "Spring-Sleuth");
		Skill skill4 = new Skill("SD-4", "Axon");
		Skill skill5 = new Skill("SD-5", "Istio");
		Skill skill6 = new Skill("SD-6", "Serf");
		Skill skill7 = new Skill("SD-7", "Gossip");
		Skill skill8 = new Skill("SD-9", "Raft");
		Skill skill9 = new Skill("SD-9", "Paxos");
		Skill skill10 = new Skill("SD-9", "Kafka");
		
		skills.add(skill1);
		skills.add(skill2);
		skills.add(skill3);
		skills.add(skill4);
		skills.add(skill5);
		skills.add(skill6);
		skills.add(skill7);
		skills.add(skill8);
		skills.add(skill9);
		skills.add(skill10);
		
		return Mono.just(skills);
	}
}
