package com.sonal.oidc.spring.okta.productservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtBearerTokenAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.OpaqueTokenAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;


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
	private OAuth2ClientProperties oAuth2ClientProperties;
	
	//@Autowired
	//private JwtDecoder jwtDecoder;
	
	//@Autowired
	//private OpaqueTokenIntrospector opaqueTokenIntrospector;
	
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
       
        // Send a 401 message to the browser (w/o this, you'll see a blank page)
        //Okta.configureResourceServer401ResponseBody(http);
        
       // configure a custom authentication manager resolver. For dynamically validating JWT token locally or by remote introspection (Opaque Token case) 
       http.oauth2ResourceServer().authenticationManagerResolver(customAuthenticationManager());
    }
    
    
    AuthenticationManagerResolver<HttpServletRequest> customAuthenticationManager() {
    	
        LinkedHashMap<RequestMatcher, AuthenticationManager> authenticationManagers = new LinkedHashMap<>();

        // USE JWT tokens (locally validated) to validate HEAD, GET, and OPTIONS requests
        List<String> readMethod = Arrays.asList("HEAD", "GET", "OPTIONS");
        RequestMatcher readMethodRequestMatcher = request -> readMethod.contains(request.getMethod());
        authenticationManagers.put(readMethodRequestMatcher, jwt());

        // all other requests will use opaque tokens (remotely validated)
        RequestMatchingAuthenticationManagerResolver authenticationManagerResolver = new RequestMatchingAuthenticationManagerResolver(authenticationManagers);
        
        authenticationManagerResolver.setDefaultAuthenticationManager(opaque());
        
        return authenticationManagerResolver;
    }
    
   
    /*
    
    AuthenticationManager jwt() {
       
    	JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        authenticationProvider.setJwtAuthenticationConverter(new JwtBearerTokenAuthenticationConverter());
        return authenticationProvider::authenticate;
    }

    AuthenticationManager opaque() {
        
        return new OpaqueTokenAuthenticationProvider(opaqueTokenIntrospector)::authenticate;
    }
    
    
    
    @Bean
    JwtDecoder jwtDecoder() {
    	// this is the keys endpoint for okta
        String issuer = oAuth2ClientProperties.getProvider().get("okta").getIssuerUri();
        String jwkSetUri = issuer + "/v1/keys";

        NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();

        // okta recommends validating the `iss` and `aud` claims
        // see: https://developer.okta.com/docs/guides/validate-access-tokens/java/overview/
        List<OAuth2TokenValidator<Jwt>> validators = new ArrayList<>();
        validators.add(new JwtTimestampValidator());
        validators.add(new JwtIssuerValidator(issuer));
        validators.add(token -> {
            Set<String> expectedAudience = new HashSet<>();
            expectedAudience.add("api://default"); // this is the default value, update this accordingly
            return !Collections.disjoint(token.getAudience(), expectedAudience)
                    ? OAuth2TokenValidatorResult.success()
                    : OAuth2TokenValidatorResult.failure(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST,
                        "This aud claim is not equal to the configured audience",
                        "https://tools.ietf.org/html/rfc6750#section-3.1"));
        });
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(validators);
        nimbusJwtDecoder.setJwtValidator(validator);
        
        return nimbusJwtDecoder;
    }
    
    @Bean
    OpaqueTokenIntrospector opaqueTokenIntrospector() {
    	
    	 String issuer = oAuth2ClientProperties.getProvider().get("okta").getIssuerUri();
         String introspectionUri = issuer + "/v1/introspect";

         // The default opaque token logic
         OAuth2ClientProperties.Registration oktaRegistration = oAuth2ClientProperties.getRegistration().get("okta");
         OpaqueTokenIntrospector introspectionClient = new NimbusOpaqueTokenIntrospector(
                 introspectionUri,
                 oktaRegistration.getClientId(),
                 oktaRegistration.getClientSecret());
         
         return introspectionClient;
    }
    */
    
    
    AuthenticationManager jwt() {
        // this is the keys endpoint for okta
        String issuer = oAuth2ClientProperties.getProvider().get("okta").getIssuerUri();
        String jwkSetUri = issuer + "/v1/keys";

        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();

        // okta recommends validating the `iss` and `aud` claims
        // see: https://developer.okta.com/docs/guides/validate-access-tokens/java/overview/
        List<OAuth2TokenValidator<Jwt>> validators = new ArrayList<>();
        validators.add(new JwtTimestampValidator());
        validators.add(new JwtIssuerValidator(issuer));
        validators.add(token -> {
            Set<String> expectedAudience = new HashSet<>();
            expectedAudience.add("com.okta.oidc.svc.product"); // this is the default value, update this accordingly
            return !Collections.disjoint(token.getAudience(), expectedAudience)
                    ? OAuth2TokenValidatorResult.success()
                    : OAuth2TokenValidatorResult.failure(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST,
                        "This aud claim is not equal to the configured audience",
                        "https://tools.ietf.org/html/rfc6750#section-3.1"));
        });
        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(validators);
        jwtDecoder.setJwtValidator(validator);

        JwtAuthenticationProvider authenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
        authenticationProvider.setJwtAuthenticationConverter(new JwtBearerTokenAuthenticationConverter());
        return authenticationProvider::authenticate;
    }

    AuthenticationManager opaque() {
        String issuer = oAuth2ClientProperties.getProvider().get("okta").getIssuerUri();
        String introspectionUri = issuer + "/v1/introspect";

        // The default opaque token logic
        OAuth2ClientProperties.Registration oktaRegistration = oAuth2ClientProperties.getRegistration().get("okta");
        OpaqueTokenIntrospector introspectionClient = new NimbusOpaqueTokenIntrospector(
                introspectionUri,
                oktaRegistration.getClientId(),
                oktaRegistration.getClientSecret());
        return new OpaqueTokenAuthenticationProvider(introspectionClient)::authenticate;
    }
    
    
    @Bean
    OAuth2ClientProperties oAuth2ClientProperties() {
        return new OAuth2ClientProperties();
    }
    

}
