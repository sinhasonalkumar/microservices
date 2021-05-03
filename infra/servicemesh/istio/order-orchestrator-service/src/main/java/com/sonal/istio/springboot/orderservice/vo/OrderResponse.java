package com.sonal.istio.springboot.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sonal.istio.springboot.orderservice.client.vo.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonInclude(value = Include.NON_NULL)
@ToString(includeFieldNames = true)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
	
	private String orderId;
	
	private String message;
	
	private ProductResponse orderDetails;
}
