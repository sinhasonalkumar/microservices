package com.sonal.es.axon.spring.saga.commands.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PrepareShipmentRequestVO {

	private String orderId;
	
	private String destination;
}
