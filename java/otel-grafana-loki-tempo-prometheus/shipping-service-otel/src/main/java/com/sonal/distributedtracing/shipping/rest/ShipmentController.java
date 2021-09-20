package com.sonal.distributedtracing.shipping.rest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.distributedtracing.shipping.service.ShippingService;
import com.sonal.distributedtracing.shipping.vo.ShippingRequestVO;
import com.sonal.distributedtracing.shipping.vo.ShippingResponseVO;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/shipment")
public class ShipmentController {
	
	
	@Autowired
	private ShippingService shippingService;
	
	@PostMapping
	public ResponseEntity<ShippingResponseVO> shipOrderRequest(@RequestBody ShippingRequestVO shippingRequestVO){
		
		
		log.info("started");
		
		ResponseEntity<ShippingResponseVO> response = ResponseEntity.ok(shippingService.shipOrderRequest(shippingRequestVO));
		
		log.info("Ended");
		
		return response;
	}
	
}
