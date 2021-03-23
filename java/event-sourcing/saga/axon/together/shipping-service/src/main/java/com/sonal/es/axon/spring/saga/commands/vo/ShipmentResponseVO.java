package com.sonal.es.axon.spring.saga.commands.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ShipmentResponseVO {

	private String shipmentId;
	
	private String message;
}
