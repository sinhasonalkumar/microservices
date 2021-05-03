package com.sonal.istio.springboot.paymentservice.persistence.bo;

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
public class PaymentBO implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7532185739093789098L;

	private String accountId;
	
	private String ccNumber;
	
	private String token;
	
}
