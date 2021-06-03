package com.sonal.spring.security.rs.oidc.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sonal.spring.security.rs.oidc.vo.ProductResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductServiceClient {
	
	private RestTemplate restTemplate;
	
	
	public ResponseEntity<ProductResponse> listAllProducts() {
		
		ResponseEntity<ProductResponse> resp = restTemplate.getForEntity("http://localhost:8080/product-service/product/list", ProductResponse.class);
		
		return resp;
	}
	
}
