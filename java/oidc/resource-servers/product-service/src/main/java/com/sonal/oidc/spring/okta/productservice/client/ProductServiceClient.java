package com.sonal.oidc.spring.okta.productservice.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sonal.oidc.spring.okta.productservice.vo.ProductResponse;

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
