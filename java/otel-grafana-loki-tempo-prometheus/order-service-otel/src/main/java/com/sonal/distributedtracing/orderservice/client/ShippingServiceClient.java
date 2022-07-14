package com.sonal.distributedtracing.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sonal.distributedtracing.orderservice.annotation.LogTimeElapsed;
import com.sonal.distributedtracing.orderservice.client.vo.ShippingRequestVO;
import com.sonal.distributedtracing.orderservice.client.vo.ShippingResponseVO;
import com.sonal.distributedtracing.orderservice.vo.OrderRequestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShippingServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private Tracer tracer;
	
	@Value("${orderservice.client.shippingservice.base.url:http://localhost:8081/shipping-service}")
	private String shippingServiceBaseURL;
	
	@LogTimeElapsed
	public ShippingResponseVO ship(OrderRequestVO orderRequestVO) {
		
//		tracer.currentSpan().tag("peer.service", "shipping-service");
//		tracer.currentSpan().tag("productId", orderRequestVO.getProductId());
//		tracer.currentSpan().name("PlaceShippingRequest");
//		tracer.currentSpan().annotate("REST_CALL_SHIPPING_SERVICE_TO_PLACE_SHIPPING_REQ");
		
		
		ShippingRequestVO shippingRequest = ShippingRequestVO.builder()
															 .productId(orderRequestVO.getProductId())
															 .build();
		
		log.debug("Shipping Request = " + shippingRequest);
		
		ResponseEntity<ShippingResponseVO> shippingResponseEntity = restTemplate.postForEntity(shippingServiceBaseURL + "/shipment", shippingRequest, ShippingResponseVO.class);
		
		ShippingResponseVO shippingResponseVO = shippingResponseEntity.getBody();
		
		log.debug("Shipping Response = " + shippingRequest);
		
		return shippingResponseVO;
		
	}
}