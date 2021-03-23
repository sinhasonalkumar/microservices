package com.sonal.es.axon.spring.saga.commands.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.es.axon.spring.saga.commands.service.ShipmentCommandService;
import com.sonal.es.axon.spring.saga.commands.vo.PrepareShipmentRequestVO;
import com.sonal.es.axon.spring.saga.commands.vo.ShipmentResponseVO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/shipment")
public class ShipmentCommandController {

	@Autowired
	private ShipmentCommandService shipmentService;
	
	@PostMapping("/prepare")
	public ResponseEntity<ShipmentResponseVO> prepareShipment(@RequestBody PrepareShipmentRequestVO shipmentRequest){
		
		ShipmentResponseVO prepareShipment = shipmentService.prepareShipment(shipmentRequest);
		
		return ResponseEntity.ok().body(prepareShipment);
	}
	
	
	@PatchMapping("/delivered/{shipmentId}/{orderId}")
	public ResponseEntity<ShipmentResponseVO> shipmentDelivered(@PathVariable("shipmentId") String shipmentId, @PathVariable("orderId") String orderId){
		
		ShipmentResponseVO prepareShipment = shipmentService.shipmentDelivered(shipmentId, orderId);
		
		return ResponseEntity.ok().body(prepareShipment);
	}
}
