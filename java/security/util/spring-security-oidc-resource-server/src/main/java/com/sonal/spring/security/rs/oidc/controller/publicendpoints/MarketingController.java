package com.sonal.spring.security.rs.oidc.controller.publicendpoints;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/public")
public class MarketingController {

	
	@GetMapping("/product/brand/info")
	public ResponseEntity<String> productBrandInfo(){
		
		return ResponseEntity.ok("Product Brand Info ");
	}
}
