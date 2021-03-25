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
public class ShippingRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4995300779289737222L;

	private String productId;
	
	private String fullName;
	
	private String address;
}
