package com.sonal.es.axon.spring.saga.commands.service;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.es.axon.spring.saga.commands.cmds.PrepareShipmentCommand;
import com.sonal.es.axon.spring.saga.commands.cmds.ShipmentDeliveryCommand;
import com.sonal.es.axon.spring.saga.commands.vo.PrepareShipmentRequestVO;
import com.sonal.es.axon.spring.saga.commands.vo.ShipmentResponseVO;

@Service
public class ShipmentCommandService {
	
	@Autowired
	private CommandGateway commandGateway; 

	public ShipmentResponseVO prepareShipment(PrepareShipmentRequestVO shipmentRequest) {
		
		String shipmentId = UUID.randomUUID().toString();
		
		PrepareShipmentCommand command = PrepareShipmentCommand.builder()
															   .shipmentId(shipmentId)
															   .orderId(shipmentRequest.getOrderId())
															   .destination(shipmentRequest.getDestination())
															   .build();
		
		shipmentId = String.class.cast(commandGateway.sendAndWait(command));
		
		return  ShipmentResponseVO.builder()
								  .shipmentId(shipmentId)
								  .message("Shipment has been created with ShipmentId : " + shipmentId + " for order Id " + shipmentRequest.getOrderId())
								  .build();
	}
	
	public ShipmentResponseVO shipmentDelivered(String shipmentId, String orderId) {
		
		ShipmentDeliveryCommand shipmentDeliveryCommand = ShipmentDeliveryCommand.builder()
																				 .shipmentId(shipmentId)
																				 .orderId(orderId)
																				 .build();
		
		commandGateway.sendAndWait(shipmentDeliveryCommand);
		
		return ShipmentResponseVO.builder()
								 .shipmentId(shipmentId)
								 .message("Shipment has been marked as Delivered")
								 .build();
		
	}
}
