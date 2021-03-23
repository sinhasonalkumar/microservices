package com.sonal.es.axon.spring.saga.commands.cmds;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterShipmentWithOrderCommand {

	@TargetAggregateIdentifier
	private String orderId;
	
	private String shipmentId;
	
}
