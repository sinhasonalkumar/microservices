package com.sonal.es.axon.spring.saga.commands.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PlaceOrderRequestVO {

	private String productName;
	
	private String productId;

	private String destinationAddress;
}
