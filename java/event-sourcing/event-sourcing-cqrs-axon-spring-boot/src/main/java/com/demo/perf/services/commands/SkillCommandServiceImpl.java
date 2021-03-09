package com.demo.perf.services.commands;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.demo.perf.commands.skill.CreateSkillCommand;
import com.demo.perf.commands.skill.CreateSkillItemCommand;
import com.demo.perf.commands.skill.DeleteSkillCommand;
import com.demo.perf.commands.skill.UpdateSkillCommand;
import com.demo.perf.commands.skill.UpdateSkillItemCommand;
import com.demo.perf.dto.commands.SkillCreateDTO;
import com.demo.perf.dto.commands.SkillItemCreateDTO;
import com.demo.perf.dto.commands.SkillItemUpdateDTO;
import com.demo.perf.dto.commands.SkillUpdateDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SkillCommandServiceImpl implements SkillCommandService {

	private final CommandGateway commandGateway;

	@Override
	public CompletableFuture<String> createSkill(SkillCreateDTO skillCreateDTO) {

		return commandGateway.send(new CreateSkillCommand(UUID.randomUUID().toString(), skillCreateDTO.getName(),
				skillCreateDTO.getDescription(), skillCreateDTO.getCapabilityId(),
				buildNew(skillCreateDTO.getSkillItems())));
	}

	@Override
	public CompletableFuture<String> updateSkill(SkillUpdateDTO skillUpdateDTO, String skillId) {
		return commandGateway.send(new UpdateSkillCommand(skillId, skillUpdateDTO.getName(),skillUpdateDTO.getDescription(), skillUpdateDTO.getCapabilityId(), buildUpdate(skillUpdateDTO.getSkillItems())));
	}

	@Override
	public CompletableFuture<String> deleteSkill(String skillId) {
		return commandGateway.send(new DeleteSkillCommand(skillId));
	}

	private List<CreateSkillItemCommand> buildNew(List<SkillItemCreateDTO> listSkillItemCreateDTO) {

		return listSkillItemCreateDTO.stream().map(si -> buildNew(si)).collect(Collectors.toList());
	}

	private CreateSkillItemCommand buildNew(SkillItemCreateDTO si) {
		return new CreateSkillItemCommand(UUID.randomUUID().toString(), si.getName(), si.getDescription());
	}
	
	
	private List<UpdateSkillItemCommand> buildUpdate(List<SkillItemUpdateDTO> listSkillItemCreateDTO) {

		return listSkillItemCreateDTO.stream().map(si -> buildUpdate(si)).collect(Collectors.toList());
	}

	private UpdateSkillItemCommand buildUpdate(SkillItemUpdateDTO si) {
		return new UpdateSkillItemCommand(si.getId(), si.getName(), si.getDescription());
	}
	
	

}
