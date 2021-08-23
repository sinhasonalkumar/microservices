package com.sonal.distributedtracing.orderservice.client.vo;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ShippingRequestVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -538579935395227023L;
	
	private String productId;
	

}
