package com.sonal.oidc.spring.okta.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.oidc.spring.okta.productservice.vo.ProductResponse;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/admin/product")
public class ProductAdminEndpoint {

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping(value = "/cache/refresh")
	public ResponseEntity<ProductResponse> clearCache(){
		
		ProductResponse response = ProductResponse.builder()
													 .message("CacheRefreshed")
													 .build();
		
		return ResponseEntity.ok(response);
	}
}
