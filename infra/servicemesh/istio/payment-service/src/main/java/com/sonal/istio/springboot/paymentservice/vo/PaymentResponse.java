package com.sonal.istio.springboot.paymentservice.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1290439684328188335L;

	private String confirmationNumber;
	
	private String message;
}
