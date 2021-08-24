package com.sonal.distributedtracing.shipping.rest;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.distributedtracing.shipping.service.ShippingService;
import com.sonal.distributedtracing.shipping.vo.ShippingRequestVO;
import com.sonal.distributedtracing.shipping.vo.ShippingResponseVO;

import brave.Tracer;
import brave.baggage.BaggageField;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/shipment")
public class ShipmentController {

	@Autowired
	private Tracer tracer;
	
	@Autowired
	private ShippingService shippingService;
	
	@PostMapping
	public ResponseEntity<ShippingResponseVO> shipOrderRequest(@RequestBody ShippingRequestVO shippingRequestVO){
		
		
		printBaggages();
		
		log.info("Active Trace : " + tracer.currentSpan().context().traceIdString());
		log.info("Active Span : " + tracer.currentSpan().context().spanIdString());
		
		log.info("started");
		
		ResponseEntity<ShippingResponseVO> response = ResponseEntity.ok(shippingService.shipOrderRequest(shippingRequestVO));
		
		log.info("Ended");
		
		return response;
	}
	
	private void printBaggages() {
		
		 Map<String, String> allValues = BaggageField.getAllValues();
		 
		 StringBuilder baggages = new StringBuilder(); 
		 
		 allValues.keySet()
		 		  .stream()
		 		  .forEach(b -> baggages.append(b + "=" + allValues.get(b)));
		 
		 log.info("Baggages : " + baggages);
	}
}
