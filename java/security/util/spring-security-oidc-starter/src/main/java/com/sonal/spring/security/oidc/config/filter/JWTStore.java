package com.sonal.spring.security.oidc.config.filter;

import lombok.Data;

@Data
public class JWTStore {

	private String jwt;
	
	public void clear() {
	    this.jwt = null;
	}

}
