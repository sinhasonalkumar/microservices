package com.sonal.oidc.spring.okta.productservice.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.oauth2.server.resource.introspection.NimbusOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

@Configuration
public class Oauth2Config {

	@Autowired
	private OAuth2ClientProperties oAuth2ClientProperties;
	
	@Value("${okta.oauth2.audience}")
	private String oauth2Audience;
	
	@Value("${okta.oauth2.resourceserver.opaquetoken.introspection-uri}")
	private String introspectionUri;

	@Bean
	public JwtDecoder jwtDecoder() {
		// this is the keys endpoint for okta
		String issuer = oAuth2ClientProperties.getProvider().get("okta").getIssuerUri();

		// String jwkSetUri = issuer + "/v1/keys";
		String jwkSetUri = oAuth2ClientProperties.getProvider().get("okta").getJwkSetUri();

		NimbusJwtDecoder nimbusJwtDecoder = NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();

		// okta recommends validating the `iss` and `aud` claims
		// see:
		// https://developer.okta.com/docs/guides/validate-access-tokens/java/overview/
		List<OAuth2TokenValidator<Jwt>> validators = new ArrayList<>();
		
		validators.add(new JwtTimestampValidator());
		
		validators.add(new JwtIssuerValidator(issuer));
		
		validators.add(token -> {
			
			Set<String> expectedAudience = new HashSet<>();
			expectedAudience.add(oauth2Audience);
			
			return !Collections.disjoint(token.getAudience(), expectedAudience) ? OAuth2TokenValidatorResult.success()
					: OAuth2TokenValidatorResult.failure(new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST,
																		 "This aud claim is not equal to the configured audience",
																		 "https://tools.ietf.org/html/rfc6750#section-3.1"));
		});
		
		OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(validators);
		
		nimbusJwtDecoder.setJwtValidator(validator);

		return nimbusJwtDecoder;
	}

	@Bean
	public OpaqueTokenIntrospector opaqueTokenIntrospector() {

		//String issuer = oAuth2ClientProperties.getProvider().get("okta").getIssuerUri();
		//String introspectionUri = issuer + "/v1/introspect";

		// The default opaque token logic
		OAuth2ClientProperties.Registration oktaRegistration = oAuth2ClientProperties.getRegistration().get("okta");

		OpaqueTokenIntrospector introspectionClient = new NimbusOpaqueTokenIntrospector(introspectionUri,
				                                                                        oktaRegistration.getClientId(),
				                                                                        oktaRegistration.getClientSecret());

		return introspectionClient;
	}

	@Bean
	public OAuth2ClientProperties oAuth2ClientProperties() {
		return new OAuth2ClientProperties();
	}
}
