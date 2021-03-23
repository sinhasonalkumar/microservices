package com.sonal.es.axon.spring.saga.queries.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryBackpressure;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.stereotype.Service;

import com.sonal.es.axon.spring.saga.queries.entity.ShipmentBO;
import com.sonal.es.axon.spring.saga.queries.repository.queries.shipment.FindShipmentByIdQuery;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ShipmentQueryService {

	
	private final QueryGateway queryGateway;
	
	public CompletableFuture<ShipmentBO> findByShipmentId(String shipmentId) {
		return queryGateway.query(FindShipmentByIdQuery.builder()
													   .shipmentId(shipmentId)
													   .build(), ShipmentBO.class);
	}
	
	public Flux<ShipmentBO> listAllShipments() {
		 SubscriptionQueryResult<List<ShipmentBO>, ShipmentBO> response = queryGateway.subscriptionQuery("listAllShipments",
				 																						  null,
				 																						  ResponseTypes.multipleInstancesOf(ShipmentBO.class),
				 																						  ResponseTypes.instanceOf(ShipmentBO.class),
				 																						  SubscriptionQueryBackpressure.defaultBackpressure());
		 return response.initialResult()
				 		.flatMapMany(Flux::fromIterable)
				 		.concatWith(response.updates());
	}
}
