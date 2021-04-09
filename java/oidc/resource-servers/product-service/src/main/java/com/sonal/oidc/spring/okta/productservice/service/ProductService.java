package com.sonal.oidc.spring.okta.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sonal.oidc.spring.okta.productservice.persistence.bo.ProductBO;
import com.sonal.oidc.spring.okta.productservice.repository.ProductRepository;
import com.sonal.oidc.spring.okta.productservice.vo.ProductResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductService {

	private ProductRepository productRepository;
	
	public ProductResponse getAllProducts() {
		
		List<ProductBO> products = productRepository.findAll();
		
		return ProductResponse.builder()
							   .products(products)
							   .message("Success")
							   .build();
						 
	}
}
