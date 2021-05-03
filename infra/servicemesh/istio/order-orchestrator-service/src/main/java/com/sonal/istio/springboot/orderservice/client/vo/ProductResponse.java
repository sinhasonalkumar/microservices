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
public class ProductResponse implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4467904818982349882L;

	private String productName;
	
	private String description;
	
	private Float cost;
}
