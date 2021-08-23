package com.sonal.distributedtracing.orderservice.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.distributedtracing.orderservice.service.OrderService;
import com.sonal.distributedtracing.orderservice.vo.OrderRequestVO;
import com.sonal.distributedtracing.orderservice.vo.OrderResponseVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<OrderResponseVO> placeOrder(@RequestBody OrderRequestVO orderRequestVO){
		
		
		log.info("started");
		
		ResponseEntity<OrderResponseVO> response = ResponseEntity.ok(orderService.placeOrder(orderRequestVO));
		
		log.info("Ended");
		
		return response;
	}
}
