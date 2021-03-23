package com.sonal.es.axon.spring.saga.commands.cmds;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentDeliveryCommand {

	@TargetAggregateIdentifier
	private String shipmentId;
	
	private String orderId;
}
