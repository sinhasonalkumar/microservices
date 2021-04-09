package com.sonal.oidc.spring.okta.productservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableWebSecurity // tells spring we are going to use Spring Security to provide web security mechanisms
@EnableResourceServer // convenient annotation that enables request authentication through OAuth 2.0 tokens. Otherwise it will try redirect to login page
@Configuration
public class SecurityConfig {

}
