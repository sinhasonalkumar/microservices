package com.sonal.istio.springboot.orderservice.client.vo;

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
public class ShippingRequest implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9043641436930956547L;

	private String productId;
	
	private String fullName;
	
	private String address;
}
