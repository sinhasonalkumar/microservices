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
@Builder
@Data
public class ShippingRequestVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -538579935395227023L;
	
	private String productId;
	

}
