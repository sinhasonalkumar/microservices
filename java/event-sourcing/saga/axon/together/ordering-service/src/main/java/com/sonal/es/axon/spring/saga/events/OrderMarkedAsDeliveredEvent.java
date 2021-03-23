package com.sonal.es.axon.spring.saga.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMarkedAsDeliveredEvent {

	private String orderId;
	
	private String orderStatus;
}
