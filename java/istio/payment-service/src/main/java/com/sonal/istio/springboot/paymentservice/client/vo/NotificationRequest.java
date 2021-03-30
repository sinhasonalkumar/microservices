package com.sonal.istio.springboot.paymentservice.client.vo;


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
public class NotificationRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 176620367481053412L;

	private String email;
	
	private String message;
	
}
