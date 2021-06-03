package com.sonal.spring.security.oidc.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@ConditionalOnProperty(name="myorg.security.oidc.enabled", havingValue="false",matchIfMissing = true)
@Configuration
public class AllowAllResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
	

	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
        	.anyRequest()
        	.permitAll();
            
    }
}
