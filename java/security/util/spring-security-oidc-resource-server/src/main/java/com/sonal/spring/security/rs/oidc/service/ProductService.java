package com.sonal.spring.security.rs.oidc.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sonal.spring.security.rs.oidc.client.ProductServiceClient;
import com.sonal.spring.security.rs.oidc.persistence.bo.ProductBO;
import com.sonal.spring.security.rs.oidc.repository.ProductRepository;
import com.sonal.spring.security.rs.oidc.vo.ProductResponse;

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
