package com.sonal.istio.springboot.orderservice.client.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3700067394081738929L;

	private String confirmationNumber;
	
	private String message;
}
