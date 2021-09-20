package com.sonal.distributedtracing.shipping.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
