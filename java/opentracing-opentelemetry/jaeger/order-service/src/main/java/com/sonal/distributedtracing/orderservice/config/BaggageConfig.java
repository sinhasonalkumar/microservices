package com.sonal.distributedtracing.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig.SingleCorrelationField;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext.ScopeDecorator;

@Configuration
public class BaggageConfig {

	@Bean
	public BaggageField productIdField() {
	    return BaggageField.create("productId");
	}

	@Bean
	public ScopeDecorator mdcScopeDecorator() {
	    return MDCScopeDecorator.newBuilder()
	            .clear()
	            .add(SingleCorrelationField.newBuilder(productIdField())
	                    .flushOnUpdate()
	                    .build())
	            .build();
	}
}
