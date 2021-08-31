package com.sonal.distributedtracing.orderservice.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -484754773061791046L;
	
	private String orderId;
	
	private String message;

}
