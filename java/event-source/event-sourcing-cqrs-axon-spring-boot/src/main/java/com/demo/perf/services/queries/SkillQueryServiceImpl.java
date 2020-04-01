package com.demo.perf.services.queries;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import com.demo.perf.entities.SkillQueryEnitity;
import com.demo.perf.query.skill.FindSkillByIdQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SkillQueryServiceImpl implements SkillQueryService {

	private final EventStore eventStore;

	private final QueryGateway queryGateway;

	@Override
	public List<Object> listEventsForSkill(String skillId) {
		return eventStore.readEvents(skillId).asStream().map( s -> s.getPayload()).collect(Collectors.toList());
	}

	@Override
	public CompletableFuture<SkillQueryEnitity> getSkill(String skillId) {
		
		return queryGateway.query(new FindSkillByIdQuery(skillId), SkillQueryEnitity.class);
	}

}
