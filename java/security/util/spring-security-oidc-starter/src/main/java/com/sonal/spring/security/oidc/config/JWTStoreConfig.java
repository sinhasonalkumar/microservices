package com.sonal.spring.security.oidc.config;

import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.target.ThreadLocalTargetSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import com.sonal.spring.security.oidc.config.filter.JWTFilter;
import com.sonal.spring.security.oidc.config.filter.JWTStore;

@ConditionalOnProperty(name="myorg.security.oidc.enabled", havingValue="true")
@Configuration
public class JWTStoreConfig {

	@Bean
	public Filter jwtFilter() {
		return new JWTFilter();
	}
	
	@Bean(name = "jwtStore")
	@Scope(scopeName = "prototype")
	public JWTStore jwtStore() {
		return new JWTStore();
	}

	@Bean
	public FilterRegistrationBean tenantFilterRegistration() {
		FilterRegistrationBean result = new FilterRegistrationBean();
		result.setFilter(this.jwtFilter());
		result.setUrlPatterns(Arrays.asList("/*"));
		result.setName("JWT Store Filter");
		result.setOrder(1);
		return result;
	}

	@Bean(destroyMethod = "destroy")
	public ThreadLocalTargetSource threadLocalTenantStore() {
		ThreadLocalTargetSource result = new ThreadLocalTargetSource();
		result.setTargetBeanName("jwtStore");
		return result;
	}

	@Primary
	@Bean(name = "proxiedThreadLocalTargetSource")
	public ProxyFactoryBean proxiedThreadLocalTargetSource(ThreadLocalTargetSource threadLocalTargetSource) {
		ProxyFactoryBean result = new ProxyFactoryBean();
		result.setTargetSource(threadLocalTargetSource);
		return result;
	}	
}
