package com.sonal.distributedtracing.shipping.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonal.distributedtracing.shipping.vo.ShippingRequestVO;
import com.sonal.distributedtracing.shipping.vo.ShippingResponseVO;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShippingService {
	
	@Autowired
	private Tracer tracer;

	public ShippingResponseVO shipOrderRequest(ShippingRequestVO orderRequestVO) {
		
		String shipmentId = UUID.randomUUID().toString();
		
		log.info("ShippingService  : Order Id  " +  orderRequestVO.getProductId() + " shipmentId" + shipmentId );
		tracer.currentSpan().tag("productId", orderRequestVO.getProductId());
		tracer.currentSpan().tag("shipmentId", shipmentId);
		
		try {
			Thread.sleep(generateRandomDelay());
		} catch (InterruptedException e) {
			
		}
		
		return ShippingResponseVO.builder()
				  .shipmentId(shipmentId)
	              .message("Shipment Successfully Created")
	              .build();
	}
	
	public Long generateRandomDelay() {
		
		long leftLimit = 0L;
	    long rightLimit = 4000L;
	    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	    
	    return generatedLong;
	}
}
