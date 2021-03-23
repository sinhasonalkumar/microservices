package com.sonal.es.axon.spring.saga.queries.handlers.eventhandlers;

import java.time.Instant;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.Timestamp;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import com.sonal.es.axon.spring.saga.events.OrderMarkedAsDeliveredEvent;
import com.sonal.es.axon.spring.saga.events.OrderPlacedEvent;
import com.sonal.es.axon.spring.saga.events.ShipmentRegisteredWithOrderEvent;
import com.sonal.es.axon.spring.saga.queries.entity.OrderBO;
import com.sonal.es.axon.spring.saga.queries.repository.OrderRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderEventHandler {

	private final OrderRepository orderRepository;
	
	private final QueryUpdateEmitter updateEmitter;
	
	@EventHandler
	public void on(OrderPlacedEvent orderPlacedEvent, @Timestamp Instant timestamp) {
		
		OrderBO orderBO = OrderBO.builder()
										  .destinationAddress(orderPlacedEvent.getDestinationAddress())
										  .orderedOn(timestamp)
										  .orderId(orderPlacedEvent.getOrderId())
										  .orderStatus(orderPlacedEvent.getOrderStatus())
										  .productId(orderPlacedEvent.getProductId())
										  .productName(orderPlacedEvent.getProductName())
										  .shipmentId(orderPlacedEvent.getShipmentId())
										  .build();
		
		orderRepository.save(orderBO);
		updateEmitter.emit(m -> "listAllOrders".equals(m.getQueryName()), orderBO);
	}
	
	@EventHandler
	public void on(ShipmentRegisteredWithOrderEvent shipmentRegisteredWithOrderEvent) {
		
		OrderBO orderBO = orderRepository.findById(shipmentRegisteredWithOrderEvent.getOrderId()).get();
		
		orderBO.setOrderStatus(shipmentRegisteredWithOrderEvent.getOrderStatus());
		
		orderRepository.save(orderBO);
		
		updateEmitter.emit(m -> "listAllOrders".equals(m.getQueryName()), orderBO);
		
	}
	
	@EventHandler
	public void on(OrderMarkedAsDeliveredEvent orderMarkedAsDeliveredEvent) {
		
		OrderBO orderBO = orderRepository.findById(orderMarkedAsDeliveredEvent.getOrderId()).get();
		
		orderBO.setOrderStatus(orderMarkedAsDeliveredEvent.getOrderStatus());
		
		orderRepository.save(orderBO);
		
		updateEmitter.emit(m -> "listAllOrders".equals(m.getQueryName()), orderBO);
	}
	
}
