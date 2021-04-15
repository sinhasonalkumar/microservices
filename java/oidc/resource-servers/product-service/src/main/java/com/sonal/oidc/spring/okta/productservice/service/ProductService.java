package com.sonal.oidc.spring.okta.productservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sonal.oidc.spring.okta.productservice.client.ProductServiceClient;
import com.sonal.oidc.spring.okta.productservice.persistence.bo.ProductBO;
import com.sonal.oidc.spring.okta.productservice.repository.ProductRepository;
import com.sonal.oidc.spring.okta.productservice.vo.ProductResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {

	private ProductRepository productRepository;
	
	private ProductServiceClient productServiceClient;
	
	public ProductResponse getAllProducts() {
		
		List<ProductBO> products = productRepository.findAll();
		
		return ProductResponse.builder()
							   .products(products)
							   .message("Success")
							   .build();
						 
	}
	
	public ResponseEntity<ProductResponse> getAllProductsRemotly() {
		
		return productServiceClient.listAllProducts();
						 
	}
}
