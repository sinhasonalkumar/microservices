package com.sonal.oidc.spring.okta.productservice.controller;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.oidc.spring.okta.productservice.vo.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/admin/product")
public class ProductAdminEndpoint {
	
	private OAuth2ClientProperties oAuth2ClientProperties;

	@PreAuthorize("hasAuthority('SCOPE_product-svc-admin')")
	@GetMapping(value = "/cache/refresh")
	public ResponseEntity<ProductResponse> clearCache(){
		
		ProductResponse response = ProductResponse.builder()
													 .message("CacheRefreshed")
													 .build();
		
		return ResponseEntity.ok(response);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_product-svc-admin')")
	@GetMapping(value = "/token/details")
	public ResponseEntity<AbstractOAuth2TokenAuthenticationToken<AbstractOAuth2Token>> getTokenDetails(AbstractOAuth2TokenAuthenticationToken<AbstractOAuth2Token> token){
		
		 log.info("OAuth2ClientProperties : getProvider Details"); 
		
		 oAuth2ClientProperties.getProvider().entrySet().stream().forEach(e -> log.info(e.getKey() + " :: " + e.getValue()));
		 
		 log.info("OAuth2ClientProperties : getRegistration Details"); 
		 
		 oAuth2ClientProperties.getRegistration().entrySet().stream().forEach(e -> log.info(e.getKey() + " :: " + e.getValue()));
		 
		return ResponseEntity.ok(token);
	}
}
