package com.sonal.es.axon.spring.saga.queries.handlers.queryhandler;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.sonal.es.axon.spring.saga.queries.entity.OrderBO;
import com.sonal.es.axon.spring.saga.queries.repository.OrderRepository;
import com.sonal.es.axon.spring.saga.queries.repository.queries.shipment.FindOrderByIdQuery;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderQueryHandler {

	private OrderRepository orderRepository;
	
	@QueryHandler
	public OrderBO onFindOrderByIdQuery(FindOrderByIdQuery findOrderByIdQuery) {
		
		return orderRepository.findById(findOrderByIdQuery.getOrderId()).get();
	}
	
	@QueryHandler(queryName = "listAllOrders")
	public Iterable<OrderBO> listAllShipments(){
	
		return orderRepository.findAll();
	}
	
}
