package com.sonal.spring.security.oidc.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConditionalOnProperty(name="myorg.security.oidc.enabled", havingValue="true")
@Data
@Configuration
public class OIDCWebSecurityConfigurer {

	
	@Value("${myorg.oidc.oauth2.audience}")
	private String oauth2Audience;
	
	@Value("${myorg.oidc.oauth2.introspectionUri}")
	private String introspectionUri;
}
