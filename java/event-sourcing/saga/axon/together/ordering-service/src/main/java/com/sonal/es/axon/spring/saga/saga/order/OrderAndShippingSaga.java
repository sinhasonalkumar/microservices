package com.sonal.es.axon.spring.saga.saga.order;

import static org.axonframework.modelling.saga.SagaLifecycle.associateWith;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import com.sonal.es.axon.spring.saga.commands.cmds.MarkOrderAsDeliveredCommand;
import com.sonal.es.axon.spring.saga.commands.cmds.PrepareShipmentCommand;
import com.sonal.es.axon.spring.saga.commands.cmds.RegisterShipmentWithOrderCommand;
import com.sonal.es.axon.spring.saga.events.OrderPlacedEvent;
import com.sonal.es.axon.spring.saga.events.ShipmentDeliveredEvent;
import com.sonal.es.axon.spring.saga.events.ShipmentPreparedEvent;

@Saga
public class OrderAndShippingSaga {

	@Autowired
    private transient CommandGateway commandGateway;
	
	
	@StartSaga
	@SagaEventHandler(associationProperty = "orderId")
	public void on(OrderPlacedEvent orderPlacedEvent) {
		
		String orderId = orderPlacedEvent.getOrderId();
		String shipmentId = UUID.randomUUID().toString();
		associateWith("shipmentId", shipmentId);
		
		PrepareShipmentCommand command = PrepareShipmentCommand.builder()
															   .shipmentId(shipmentId)
															   .orderId(orderId)
															   .destination(orderPlacedEvent.getDestinationAddress())
															   .build();
		
		commandGateway.send(command);
		
	}
	
	
	@SagaEventHandler(associationProperty = "shipmentId")
	public void on(ShipmentPreparedEvent shipmentPreparedEvent) {
		
		RegisterShipmentWithOrderCommand command = RegisterShipmentWithOrderCommand.builder()
																				   .orderId(shipmentPreparedEvent.getOrderId())
																				   .shipmentId(shipmentPreparedEvent.getShipmentId())
																				   .build();
		
		commandGateway.send(command);
	}
	
	@EndSaga
	@SagaEventHandler(associationProperty = "shipmentId")
	public void on(ShipmentDeliveredEvent shipmentDeliveredEvent) {
		
		 MarkOrderAsDeliveredCommand command = MarkOrderAsDeliveredCommand.builder()
										                                  .orderId(shipmentDeliveredEvent.getOrderId())
										                                  .build();
		
		commandGateway.send(command);
	}
}
