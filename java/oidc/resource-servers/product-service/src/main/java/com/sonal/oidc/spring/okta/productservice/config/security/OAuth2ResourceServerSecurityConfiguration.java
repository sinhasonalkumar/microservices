package com.sonal.oidc.spring.okta.productservice.config.security;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtBearerTokenAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
@EnableWebSecurity // tells spring we are going to use Spring Security to provide web security mechanisms
//@EnableResourceServer // convenient annotation that enables request authentication through OAuth 2.0 tokens. Otherwise it will try redirect to login page
//Using @EnableResourceServer is deprecated in Spring Boot 2.1. You now configure a resource server using Spring Security’s config
@Configuration
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	@Autowired
	private OpaqueTokenIntrospector opaqueTokenIntrospector;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            // allow anonymous access to the root page
            .antMatchers("/**/actuator/**").permitAll()
            .antMatchers("/public/**").permitAll()
            // all other requests
            .anyRequest().authenticated();
            //.and()
            //.oauth2ResourceServer().jwt(); // replace .jwt() with .opaqueToken() for Opaque Token case
            //.oauth2ResourceServer().opaqueToken();
        	// These methods are not handy when we have mix of jwt() and opaqueToken() case
       
        // Send a 401 message to the browser (w/o this, you'll see a blank page)
        //Okta.configureResourceServer401ResponseBody(http);
        
       // configure a custom authentication manager resolver. 
       //For dynamically validating JWT token locally or by remote introspection (Opaque Token case)
       // To Get this woring we have to remove okta-spring-boot starter maven dependency 
       http.oauth2ResourceServer().authenticationManagerResolver(customAuthenticationManager());
    }
    
    
	private AuthenticationManagerResolver<HttpServletRequest> customAuthenticationManager() {
    	
        Map<RequestMatcher, AuthenticationManager> authenticationManagers = new LinkedHashMap<>();

        // USE opaque tokens (remotely validated) to validate PUT, POST, and DELETE requests
        List<String> readMethod = Arrays.asList(HttpMethod.POST.name(),
        										HttpMethod.PUT.name(),
        										HttpMethod.DELETE.name());
       
        RequestMatcher readMethodRequestMatcher = request -> readMethod.contains(request.getMethod());
        
        // USE opaque tokens (remotely validated) to validate URI fall under URI antmatcher and httpMethod as PATCH 
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/**/product/**", HttpMethod.PATCH.name());
        
        authenticationManagers.put(antPathRequestMatcher, opaqueTokenValidationByRemoteIntrospection());
        authenticationManagers.put(readMethodRequestMatcher, opaqueTokenValidationByRemoteIntrospection());
        
        RequestMatchingAuthenticationManagerResolver authenticationManagerResolver = new RequestMatchingAuthenticationManagerResolver(authenticationManagers);
        
        // all other requests will USE JWT tokens (locally validated)
        authenticationManagerResolver.setDefaultAuthenticationManager(jwtLocalValidation());
        
        return authenticationManagerResolver;
    } 
    
	private AuthenticationManager jwtLocalValidation() {
       
    	JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        authenticationProvider.setJwtAuthenticationConverter(new JwtBearerTokenAuthenticationConverter());
        return authenticationProvider::authenticate;
    }

	private AuthenticationManager opaqueTokenValidationByRemoteIntrospection() {
        
        return new OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector)::authenticate;
    } 

}
