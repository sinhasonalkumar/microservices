package com.sonal.es.axon.spring.saga.commands.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.sonal.es.axon.spring.saga.commands.aggregates.enums.OrderStatus;
import com.sonal.es.axon.spring.saga.commands.cmds.MarkOrderAsDeliveredCommand;
import com.sonal.es.axon.spring.saga.commands.cmds.PlaceOrderCommand;
import com.sonal.es.axon.spring.saga.commands.cmds.RegisterShipmentWithOrderCommand;
import com.sonal.es.axon.spring.saga.events.OrderMarkedAsDeliveredEvent;
import com.sonal.es.axon.spring.saga.events.OrderPlacedEvent;
import com.sonal.es.axon.spring.saga.events.ShipmentRegisteredWithOrderEvent;

import lombok.Data;


@Aggregate
@Data
public class OrderAggregate {

	@AggregateIdentifier
	private String orderId;
	
	private String productId;
	
	private String productName;
	
	private String destinationAddress;
	
	private String shipmentId;	
	
	private String orderStatus;
	
	
	public OrderAggregate() {}
	
	@CommandHandler
    public OrderAggregate(PlaceOrderCommand prepareShipmentCommand) {
		
        apply(OrderPlacedEvent.builder()
						   	  .orderId(prepareShipmentCommand.getOrderId())
						      .productId(prepareShipmentCommand.getProductId())
						      .productName(prepareShipmentCommand.getProductName())
						      .destinationAddress(prepareShipmentCommand.getDestinationAddress())
						      .shipmentId(null)
						      .orderStatus(OrderStatus.PLACED.name())
						      .build());
    }
	
	
	
	
	@CommandHandler
	public void on(RegisterShipmentWithOrderCommand registerShipmentWithOrderCommand) {
		
		apply(ShipmentRegisteredWithOrderEvent.builder()
											  .orderId(registerShipmentWithOrderCommand.getOrderId())
											  .shipmentId(registerShipmentWithOrderCommand.getShipmentId())
											  .orderStatus(OrderStatus.SHIPMENT_CREATED.name())
											  .build());
	}
	
	@CommandHandler
	public void on(MarkOrderAsDeliveredCommand markOrderAsDeliveredCommand) {
		
		apply(OrderMarkedAsDeliveredEvent.builder()
				  .orderId(markOrderAsDeliveredCommand.getOrderId())
				  .orderStatus(OrderStatus.DELIVERED.name())
				  .build());
	}
	
	
	@EventSourcingHandler
	public void on(OrderPlacedEvent orderPlacedEvent) {
		
		this.orderId = orderPlacedEvent.getOrderId();
		this.productId = orderPlacedEvent.getProductId();
		this.productName = orderPlacedEvent.getProductName();
		this.destinationAddress = orderPlacedEvent.getDestinationAddress();
		this.orderStatus = orderPlacedEvent.getOrderStatus();
		this.shipmentId = null;
		
	}
	
	@EventSourcingHandler
	public void on(ShipmentRegisteredWithOrderEvent shipmentRegisteredWithOrderEvent) {
		
		this.orderId = shipmentRegisteredWithOrderEvent.getOrderId();
		this.shipmentId = shipmentRegisteredWithOrderEvent.getShipmentId();
		this.orderStatus = shipmentRegisteredWithOrderEvent.getOrderStatus();
	}
	
	@EventSourcingHandler
	public void on(OrderMarkedAsDeliveredEvent orderMarkedAsDeliveredEvent) {
		
		this.orderStatus = orderMarkedAsDeliveredEvent.getOrderStatus();
	}
	
}
