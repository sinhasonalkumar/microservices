package com.sonal.oidc.spring.okta.productservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.okta.spring.boot.oauth.Okta;


@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
@EnableWebSecurity // tells spring we are going to use Spring Security to provide web security mechanisms
//@EnableResourceServer // convenient annotation that enables request authentication through OAuth 2.0 tokens. Otherwise it will try redirect to login page
//Using @EnableResourceServer is deprecated in Spring Boot 2.1. You now configure a resource server using Spring Security’s config
@Configuration
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            // allow anonymous access to the root page
            .antMatchers("/**/actuator/**").permitAll()
            // all other requests
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt(); // replace .jwt() with .opaqueToken() for Opaque Token case

        // Send a 401 message to the browser (w/o this, you'll see a blank page)
        Okta.configureResourceServer401ResponseBody(http);
    }

}
