package com.sonal.es.axon.spring.saga.queries.handlers.queryhandler;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import com.sonal.es.axon.spring.saga.queries.entity.ShipmentBO;
import com.sonal.es.axon.spring.saga.queries.repository.ShipmentRepository;
import com.sonal.es.axon.spring.saga.queries.repository.queries.shipment.FindShipmentByIdQuery;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ShipmentQueryHandler {

	private ShipmentRepository shipmentRepository;
	
	@QueryHandler
	public ShipmentBO onFindShipmentByIdQuery(FindShipmentByIdQuery findShipmentByIdQuery) {
		
		return shipmentRepository.findById(findShipmentByIdQuery.getShipmentId()).get();
	}
	
	@QueryHandler(queryName = "listAllShipments")
	public Iterable<ShipmentBO> listAllShipments(){
	
		return shipmentRepository.findAll();
	}
	
}
