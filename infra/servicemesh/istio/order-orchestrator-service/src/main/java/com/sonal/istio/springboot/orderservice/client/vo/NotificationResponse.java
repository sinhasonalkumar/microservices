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
public class NotificationResponse implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6861930680865816700L;
	
	private boolean sent;
}
