package com.sonal.es.axon.spring.saga.commands.aggregates;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import java.util.UUID;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.sonal.es.axon.spring.saga.commands.aggregates.enums.ShipmentStatus;
import com.sonal.es.axon.spring.saga.commands.cmds.PrepareShipmentCommand;
import com.sonal.es.axon.spring.saga.commands.cmds.ShipmentDeliveryCommand;
import com.sonal.es.axon.spring.saga.events.ShipmentDeliveredEvent;
import com.sonal.es.axon.spring.saga.events.ShipmentPreparedEvent;

import lombok.Data;


@Aggregate
@Data
public class ShipmentAggregate {

	@AggregateIdentifier
	private String shipmentId;
	
	private String orderId;

	private String destination;
	
	private String status;
	
	
	public ShipmentAggregate() {}
	
	@CommandHandler
    public ShipmentAggregate(PrepareShipmentCommand prepareShipmentCommand) {
		
        apply(ShipmentPreparedEvent.builder()
        						   .shipmentId(prepareShipmentCommand.getShipmentId())
        						   .orderId(prepareShipmentCommand.getOrderId())
        						   .destination(prepareShipmentCommand.getDestination())
        						   .build());
    }
	
	
	@CommandHandler
	public void on(ShipmentDeliveryCommand shipmentDeliveryCommand) {
		apply(ShipmentDeliveredEvent.builder()
									.shipmentId(shipmentDeliveryCommand.getShipmentId())
									.orderId(shipmentDeliveryCommand.getOrderId())
									.build());
			  
	}
	
	
	@EventSourcingHandler
	public void on(ShipmentPreparedEvent shipmentPreparedEvent) {
		
		this.shipmentId = shipmentPreparedEvent.getShipmentId();
		this.orderId = shipmentPreparedEvent.getOrderId();
		this.destination = shipmentPreparedEvent.getDestination();
		this.status = ShipmentStatus.CREATED.name();
	}
	
	@EventSourcingHandler
	public void on(ShipmentDeliveredEvent shipmentDeliveredEvent) {
		
		this.status = ShipmentStatus.DELIVERED.name();
	}
	
}
