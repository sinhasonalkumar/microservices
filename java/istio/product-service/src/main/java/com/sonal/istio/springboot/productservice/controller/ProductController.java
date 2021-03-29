package com.sonal.istio.springboot.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.istio.springboot.productservice.service.ProductService;
import com.sonal.istio.springboot.productservice.vo.ProductResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

	
	private ProductService productService;
	
	@GetMapping(value = "/{productId}")
	public Mono<ResponseEntity<ProductResponse>> postPayment(@PathVariable String productId){
	
		
		return productService.fetchProduct(productId)
				             .map(paymentResponse -> ResponseEntity.ok(paymentResponse));
		
	}
	
}
