package com.sonal.es.axon.spring.saga.queries.handlers.eventhandlers;

import java.time.Instant;
import java.util.Optional;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import com.sonal.es.axon.spring.saga.commands.aggregates.enums.ShipmentStatus;
import com.sonal.es.axon.spring.saga.events.ShipmentDeliveredEvent;
import com.sonal.es.axon.spring.saga.events.ShipmentPreparedEvent;
import com.sonal.es.axon.spring.saga.queries.entity.ShipmentBO;
import com.sonal.es.axon.spring.saga.queries.repository.ShipmentRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShipmentEventHandler {

	private final ShipmentRepository shipmentRepository;
	
	private final QueryUpdateEmitter updateEmitter;
	
	@EventHandler
	public void on(ShipmentPreparedEvent shipmentPreparedEvent, @Timestamp Instant timestamp) {
		
		ShipmentBO shipmentBO = ShipmentBO.builder()
										  .shipmentId(shipmentPreparedEvent.getShipmentId())
										  .orderId(shipmentPreparedEvent.getOrderId())
										  .destination(shipmentPreparedEvent.getDestination())
										  .registeredOn(timestamp)
										  .status(ShipmentStatus.CREATED.name())
										  .build();
		
		shipmentRepository.save(shipmentBO);
		updateEmitter.emit(m -> "listAllShipments".equals(m.getQueryName()), shipmentBO);
	}
	
	@EventHandler
	public void on(ShipmentDeliveredEvent shipmentDeliveredEvent, @Timestamp Instant timestamp) {
		
		Optional<ShipmentBO> shipmentBOOptional = shipmentRepository.findById(shipmentDeliveredEvent.getShipmentId());
		
		ShipmentBO shipmentBO = shipmentBOOptional.get();
		
		shipmentBO.setStatus(ShipmentStatus.DELIVERED.name());
		shipmentBO.setRegisteredOn(timestamp);
		
		shipmentRepository.save(shipmentBO);
		
		updateEmitter.emit(m -> "listAllShipments".equals(m.getQueryName()), shipmentBO);
	}
	
}
