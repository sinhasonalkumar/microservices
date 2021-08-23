package com.sonal.distributedtracing.orderservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sonal.distributedtracing.orderservice.vo.OrderRequestVO;
import com.sonal.distributedtracing.orderservice.vo.OrderResponseVO;

@Service
public class OrderService {

	public OrderResponseVO placeOrder(OrderRequestVO orderRequestVO) {
		
		String orderId = UUID.randomUUID().toString();
		
		return OrderResponseVO.builder()
				  .orderId(orderId)
	              .message("Order Successfully Placed")
	              .build();
	}
}
