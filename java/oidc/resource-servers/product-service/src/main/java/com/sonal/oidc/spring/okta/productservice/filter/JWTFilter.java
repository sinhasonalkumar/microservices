package com.sonal.oidc.spring.okta.productservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

public class JWTFilter implements Filter {

	@Autowired
	private JWTStore jwtStore;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// NoOp
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

	    HttpServletRequest request = (HttpServletRequest) servletRequest;
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
		try {
			this.jwtStore.setJwt(jwt);
			chain.doFilter(servletRequest, servletResponse);
		} finally {
		    this.jwtStore.clear();
		}
	}

	@Override
	public void destroy() {
		// NoOp
	}

}
