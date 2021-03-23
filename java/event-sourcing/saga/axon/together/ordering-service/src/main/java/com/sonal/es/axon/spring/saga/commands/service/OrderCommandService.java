package com.sonal.es.axon.spring.saga.commands.service;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.es.axon.spring.saga.commands.cmds.PlaceOrderCommand;
import com.sonal.es.axon.spring.saga.commands.vo.OrderResponseVO;
import com.sonal.es.axon.spring.saga.commands.vo.PlaceOrderRequestVO;

@Service
public class OrderCommandService {
	
	@Autowired
	private CommandGateway commandGateway; 

	public OrderResponseVO placeOrder(PlaceOrderRequestVO placeOrderRequest) {
		
		String orderId = UUID.randomUUID().toString();
		
		PlaceOrderCommand command = PlaceOrderCommand.builder()
													 .orderId(orderId)
													 .productName(placeOrderRequest.getProductName())
													 .productId(placeOrderRequest.getProductId())
													 .destinationAddress(placeOrderRequest.getDestinationAddress())
													 .build();
		
		 orderId = String.class.cast(commandGateway.sendAndWait(command));
		
		return  OrderResponseVO.builder()
								  .orderId(orderId)
								  .message("Order has been placed with OrderId : " + orderId)
								  .build();
	}
	
}
