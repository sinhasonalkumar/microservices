package com.sonal.distributedtracing.orderservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.distributedtracing.orderservice.annotation.LogTimeElapsed;
import com.sonal.distributedtracing.orderservice.client.ShippingServiceClient;
import com.sonal.distributedtracing.orderservice.client.vo.ShippingResponseVO;
import com.sonal.distributedtracing.orderservice.vo.OrderRequestVO;
import com.sonal.distributedtracing.orderservice.vo.OrderResponseVO;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ShippingServiceClient shippingServiceClient;
	
	@LogTimeElapsed
	public OrderResponseVO placeOrder(OrderRequestVO orderRequestVO) {
		
		String orderId = UUID.randomUUID().toString();
		
		log.info("Order Services : Order Id  " + orderId );
		
		tracer.currentSpan().tag("orderId", orderId);
		
		OrderResponseVO build = OrderResponseVO.builder()
				  .orderId(orderId)
	              .message("Order Successfully Placed")
	              .build();
		
		ShippingResponseVO shipment = shippingServiceClient.ship(orderRequestVO);
		
		return build;
	}
}
