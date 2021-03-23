package com.sonal.es.axon.spring.saga.commands.cmds;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrepareShipmentCommand {
	
	@TargetAggregateIdentifier
	private String shipmentId;
	
	private String orderId;

	private String destination;
}
