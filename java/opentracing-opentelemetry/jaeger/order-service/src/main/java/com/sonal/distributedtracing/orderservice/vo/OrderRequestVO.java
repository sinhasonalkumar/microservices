package com.sonal.distributedtracing.orderservice.vo;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@ToString
@Builder
@Data
public class OrderRequestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3201936651248460149L;
	
	private String productId;
	
	private String productName;
	
	

}
