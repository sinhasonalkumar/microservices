package com.sonal.es.axon.spring.saga.commands.cmds;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkOrderAsDeliveredCommand {

	@TargetAggregateIdentifier
	private String orderId;
}
