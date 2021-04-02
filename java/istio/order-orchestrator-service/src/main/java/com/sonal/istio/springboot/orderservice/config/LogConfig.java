package com.sonal.istio.springboot.orderservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brave.Tracing;
import brave.Tracing.Builder;
import brave.baggage.BaggageField;
import brave.baggage.BaggagePropagation;
import brave.baggage.BaggagePropagationConfig.SingleBaggageField;
import brave.baggage.CorrelationScopeConfig.SingleCorrelationField;
import brave.context.slf4j.MDCScopeDecorator;
import brave.propagation.B3Propagation;
import brave.propagation.CurrentTraceContext.ScopeDecorator;
import brave.propagation.ThreadLocalCurrentTraceContext;

@Configuration
public class LogConfig {

	@Bean
	public BaggageField orderServicePayload() {
		return BaggageField.create("order-service-payload");
	}

	@Bean
	public ScopeDecorator mdcScopeDecorator() {
		
		/* BaggageField ORDER_SERVICE_PAYLOAD = orderServicePayload();
		
		ScopeDecorator decorator = MDCScopeDecorator.newBuilder()
													.add(SingleCorrelationField.create(ORDER_SERVICE_PAYLOAD))
													.build();
		Builder currentTraceContext = Tracing.newBuilder().propagationFactory(BaggagePropagation.newFactoryBuilder(B3Propagation.FACTORY)
                .add(SingleBaggageField.remote(ORDER_SERVICE_PAYLOAD))
                .build())
				.currentTraceContext(ThreadLocalCurrentTraceContext.newBuilder()
                .addScopeDecorator(decorator)
                .build());

		*/
		
		return MDCScopeDecorator.newBuilder()
								.clear()
								.add(SingleCorrelationField.newBuilder(orderServicePayload())
														   //.flushOnUpdate()
														   .build())
								.build();
	}
}
