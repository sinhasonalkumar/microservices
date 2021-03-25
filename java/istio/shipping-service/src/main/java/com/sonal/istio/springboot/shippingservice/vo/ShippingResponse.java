package com.sonal.istio.springboot.shippingservice.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(includeFieldNames = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShippingResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2106519332400459475L;

	private String trackingNumber;
	
	private String carrier;
	
	private String message;
}
