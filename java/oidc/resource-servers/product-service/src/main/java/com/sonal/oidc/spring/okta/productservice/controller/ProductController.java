package com.sonal.oidc.spring.okta.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.oidc.spring.okta.productservice.service.ProductService;
import com.sonal.oidc.spring.okta.productservice.vo.ProductResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	private ProductService productService;
	
	@PreAuthorize("hasAuthority('SCOPE_product-svc')")
	@GetMapping(value = "/list")
	public ResponseEntity<ProductResponse> list(){
		
		ProductResponse allProducts = productService.getAllProducts();
		
		return ResponseEntity.ok(allProducts);
	}
}
