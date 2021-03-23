package com.sonal.es.axon.spring.saga.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPlacedEvent {

	private String productId;
	
	private String orderId;
	
	private String productName;

	private String destinationAddress;	
	
	private String orderStatus;
	
	private String shipmentId;
}
