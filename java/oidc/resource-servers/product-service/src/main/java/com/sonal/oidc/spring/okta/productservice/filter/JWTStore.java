package com.sonal.oidc.spring.okta.productservice.filter;

import lombok.Data;

@Data
public class JWTStore {

	private String jwt;
	
	public void clear() {
	    this.jwt = null;
	}

}
