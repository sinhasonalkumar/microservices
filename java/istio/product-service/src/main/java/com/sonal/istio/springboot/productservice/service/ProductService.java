package com.sonal.istio.springboot.productservice.service;

import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.productservice.repository.ProductRepository;
import com.sonal.istio.springboot.productservice.vo.ProductResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {
	
	
	private ProductRepository productRepository;

	public Mono<ProductResponse> fetchProduct(String productId) {
		
		return productRepository.findByProductId(productId)
								.map(productBO -> ProductResponse.builder()
																 .productName(productBO.getName())
																 .description(productBO.getDescription())
																 .cost(productBO.getCost())
																 .build());
								
	}
	
	
	public Flux<ProductResponse> findAllProducts() {
		
										
		return productRepository.findAllProducts()
								.map(productBO -> ProductResponse.builder()
						 	 									 .productName(productBO.getName())
						 	 									 .description(productBO.getDescription())
						 	 									 .cost(productBO.getCost())
						 	 									 .build());
	}
	
	
	
}
