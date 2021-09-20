package com.sonal.distributedtracing.orderservice.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.distributedtracing.orderservice.annotation.LogTimeElapsed;
import com.sonal.distributedtracing.orderservice.client.ShippingServiceClient;
import com.sonal.distributedtracing.orderservice.client.vo.ShippingResponseVO;
import com.sonal.distributedtracing.orderservice.vo.OrderRequestVO;
import com.sonal.distributedtracing.orderservice.vo.OrderResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	
	@Autowired
	private ShippingServiceClient shippingServiceClient;
	
	@LogTimeElapsed
	public OrderResponseVO placeOrder(OrderRequestVO orderRequestVO) {
		
		String orderId = UUID.randomUUID().toString();
		
		log.debug("Order Services : Order Id  " + orderId );
		
		OrderResponseVO build = null;
		
		try {
			ShippingResponseVO shipment = shippingServiceClient.ship(orderRequestVO);
			build = OrderResponseVO.builder()
					  .orderId(orderId)
		              .message("Order Successfully Placed")
		              .build();
		} catch (Exception e) {
			
			log.error("{\"error\" : \"Error While Calling Shipping-Service \"}");
			
			log.error("Error While Calling Shipping-Service Regular",e);
			
			build = OrderResponseVO.builder()
					  .orderId(orderId)
		              .message("Order Failed To Place")
		              .build();
		}
		
		
		return build;
	}
}
