package com.sonal.es.axon.spring.saga.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRegisteredWithOrderEvent {

	private String orderId;
	
	private String shipmentId;
	
	private String orderStatus;
}
