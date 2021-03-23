package com.sonal.es.axon.spring.saga.commands.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderResponseVO {

	private String orderId;
	
	private String orderStatus;
	
	private String message;
}
