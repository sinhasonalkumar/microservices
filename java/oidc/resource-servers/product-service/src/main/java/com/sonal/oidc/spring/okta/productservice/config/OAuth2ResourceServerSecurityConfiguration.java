package com.sonal.oidc.spring.okta.productservice.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.okta.spring.boot.oauth.Okta;

@EnableWebSecurity // tells spring we are going to use Spring Security to provide web security mechanisms
@EnableResourceServer // convenient annotation that enables request authentication through OAuth 2.0 tokens. Otherwise it will try redirect to login page
public class OAuth2ResourceServerSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            // allow anonymous access to the root page
            .antMatchers("/").permitAll()
            // all other requests
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt(); // replace .jwt() with .opaqueToken() for Opaque Token case

        // Send a 401 message to the browser (w/o this, you'll see a blank page)
        Okta.configureResourceServer401ResponseBody(http);
    }

}
