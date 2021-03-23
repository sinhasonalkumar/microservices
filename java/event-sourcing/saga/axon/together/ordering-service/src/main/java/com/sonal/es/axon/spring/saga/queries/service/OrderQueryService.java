package com.sonal.es.axon.spring.saga.queries.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryBackpressure;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.stereotype.Service;

import com.sonal.es.axon.spring.saga.queries.entity.OrderBO;
import com.sonal.es.axon.spring.saga.queries.repository.queries.shipment.FindOrderByIdQuery;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class OrderQueryService {
	
	private final QueryGateway queryGateway;
	
	public CompletableFuture<OrderBO> findByOrderId(String orderId) {
		return queryGateway.query(FindOrderByIdQuery.builder()
													   .orderId(orderId)
													   .build(), OrderBO.class);
	}
	
	public Flux<OrderBO> listAllOrders() {
		 SubscriptionQueryResult<List<OrderBO>, OrderBO> response = queryGateway.subscriptionQuery("listAllOrders",
				 																						  null,
				 																						  ResponseTypes.multipleInstancesOf(OrderBO.class),
				 																						  ResponseTypes.instanceOf(OrderBO.class),
				 																						  SubscriptionQueryBackpressure.defaultBackpressure());
		 return response.initialResult()
				 		.flatMapMany(Flux::fromIterable)
				 		.concatWith(response.updates());
	}
}
