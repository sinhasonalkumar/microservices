package com.sonal.spring.security.rs.oidc.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@ComponentScan(basePackages = "com.sonal.spring.security.rs.oidc")
@Configuration
public class GlobalAppConfig {

	@ConditionalOnProperty(name="myorg.security.oidc.enabled", havingValue="false",matchIfMissing = true)
	@Bean
	public RestTemplate restTemplate() {
		
		return new RestTemplate();
	}
}
