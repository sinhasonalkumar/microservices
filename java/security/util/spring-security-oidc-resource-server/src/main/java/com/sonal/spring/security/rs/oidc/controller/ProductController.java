package com.sonal.spring.security.rs.oidc.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.spring.security.rs.oidc.service.ProductService;
import com.sonal.spring.security.rs.oidc.vo.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/product")
public class ProductController {

	private ProductService productService;

	@PreAuthorize("hasAuthority('SCOPE_product-svc')")
	@GetMapping(value = "/list")
	//public ResponseEntity<ProductResponse> list(JwtAuthenticationToken jwt){
	public ResponseEntity<ProductResponse> list(BearerTokenAuthentication jwt){
		
		log.info("jwt information " + jwt);
		
		ProductResponse allProducts = productService.getAllProducts();

		return ResponseEntity.ok(allProducts);
	}
	
	
	@PreAuthorize("hasAuthority('SCOPE_product-svc')")
	@GetMapping(value = "/remoteList")
	//public ResponseEntity<ProductResponse> list(JwtAuthenticationToken jwt){
	public ResponseEntity<ProductResponse> remoteList(BearerTokenAuthentication jwt){
		
		log.info("jwt information " + jwt);
		
		return productService.getAllProductsRemotly();
	}

	
	@PreAuthorize("hasAuthority('SCOPE_product-svc')")
	@PatchMapping(value = "/{productId}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable String productId, BearerTokenAuthentication opaqueToken) {

		log.info("opaqueToken information " + opaqueToken);
		
		log.info("Product with ProductId : " + productId + " has been updated");
		
		return ResponseEntity.ok(ProductResponse.builder().message("Product has been updated successfully").build());
	}

}
