package com.demo.perf.services.commands;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.demo.perf.commands.capability.CreateCapabilityCommand;
import com.demo.perf.commands.capability.DeleteCapabilityCommand;
import com.demo.perf.commands.capability.UpdateCapabilityCommand;
import com.demo.perf.dto.commands.CapabilityCreateDTO;
import com.demo.perf.dto.commands.CapabilityUpdateDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CapabilityCommandServiceImpl implements CapabilityCommandService {

	private final CommandGateway commandGateway;

	@Override
	public CompletableFuture<String> createCapability(CapabilityCreateDTO capabilityCreateDTO) {

		CompletableFuture<String> response = commandGateway
				.send(new CreateCapabilityCommand(UUID.randomUUID().toString(), capabilityCreateDTO.getName(),
						capabilityCreateDTO.getDescription(), capabilityCreateDTO.getCapabilityType()));
		return response;
	}

	@Override
	public CompletableFuture<String> updateCapability(CapabilityUpdateDTO capabilityUpdateDTO, String capabilityId) {

		return commandGateway.send(new UpdateCapabilityCommand(capabilityId, capabilityUpdateDTO.getName(),
				capabilityUpdateDTO.getDescription(), capabilityUpdateDTO.getCapabilityType()));
	}

	@Override
	public CompletableFuture<String> deleteCapability(String capabilityId) {

		return commandGateway.send(new DeleteCapabilityCommand(capabilityId));
	}

}
