package com.sonal.distributedtracing.orderservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sonal.distributedtracing.orderservice.client.vo.ShippingRequestVO;
import com.sonal.distributedtracing.orderservice.client.vo.ShippingResponseVO;
import com.sonal.distributedtracing.orderservice.vo.OrderRequestVO;

@Service
public class ShippingServiceClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${service.shipping.baseURL:http://localhost:8081/shipping-service}")
	private String shippingServiceBaseURL;
	
	public ShippingResponseVO ship(OrderRequestVO orderRequestVO) {
		
		ShippingRequestVO shippingRequest = ShippingRequestVO.builder()
															 .productId(orderRequestVO.getProductId())
															 .build();
		
		ResponseEntity<ShippingResponseVO> shippingResponseEntity = restTemplate.postForEntity(shippingServiceBaseURL + "/shipment", shippingRequest, ShippingResponseVO.class);
		
		ShippingResponseVO shippingResponseVO = shippingResponseEntity.getBody();
		
		return shippingResponseVO;
		
	}
}
