package com.sonal.istio.springboot.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.baggage.BaggageField;
import brave.baggage.CorrelationScopeConfig.SingleCorrelationField;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.CurrentTraceContext.ScopeDecorator;

@Configuration
public class LogConfig {

	@Bean
	public BaggageField orderServicePayload() {
		return BaggageField.create("order-service-payload");
	}

	@Bean
	public ScopeDecorator mdcScopeDecorator() {
		return MDCScopeDecorator.newBuilder()
								.clear()
								.add(SingleCorrelationField.newBuilder(orderServicePayload())
														   .flushOnUpdate()
														   .build())
								.build();
	}
}
