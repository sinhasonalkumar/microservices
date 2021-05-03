package com.sonal.istio.springboot.productservice.persistence.bo;

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
public class ProductBO implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937942374543227312L;

	private String productId;
	
	private String name;
	
	private String description;
	
	private Float cost;
	
}
