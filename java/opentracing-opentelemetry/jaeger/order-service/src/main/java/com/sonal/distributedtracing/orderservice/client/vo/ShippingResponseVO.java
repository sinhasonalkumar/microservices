package com.sonal.distributedtracing.orderservice.client.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ShippingResponseVO implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2563602649885832961L;

	private String shipmentId;
	
	private String message;

}
