package com.sonal.es.axon.spring.saga.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentPreparedEvent {

	private String shipmentId;

	private String orderId;
	
	private String destination;
}
