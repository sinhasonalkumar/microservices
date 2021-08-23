package com.sonal.distributedtracing.shipping.vo;


import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

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
