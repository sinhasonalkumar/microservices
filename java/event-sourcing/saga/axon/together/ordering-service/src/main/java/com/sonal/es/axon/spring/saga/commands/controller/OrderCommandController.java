package com.sonal.es.axon.spring.saga.commands.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.es.axon.spring.saga.commands.service.OrderCommandService;
import com.sonal.es.axon.spring.saga.commands.vo.OrderResponseVO;
import com.sonal.es.axon.spring.saga.commands.vo.PlaceOrderRequestVO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderCommandController {

	@Autowired
	private OrderCommandService orderCommandService;
	
	@PostMapping("/")
	public ResponseEntity<OrderResponseVO> placeOrder(@RequestBody PlaceOrderRequestVO shipmentRequest){
		
		OrderResponseVO prepareShipment = orderCommandService.placeOrder(shipmentRequest);
		
		return ResponseEntity.ok().body(prepareShipment);
	}
	
}
