package com.sonal.es.axon.spring.saga.queries.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.es.axon.spring.saga.queries.entity.ShipmentBO;
import com.sonal.es.axon.spring.saga.queries.service.ShipmentQueryService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/shipment")
@AllArgsConstructor
public class ShipmentQueryController {

	private ShipmentQueryService shipmentQueryService;
	
	
	@GetMapping("/{shipmentId}")
    public CompletableFuture<ShipmentBO> getShipment(@PathVariable(value = "shipmentId") String shipmentId){
        return shipmentQueryService.findByShipmentId(shipmentId);
    }
    
    @GetMapping(value = "/list",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ShipmentBO> listAllProducts(){
        return shipmentQueryService.listAllShipments();
    }
	
}
