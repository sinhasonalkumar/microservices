package com.sonal.istio.springboot.orderservice.service;

import java.time.Instant;
import java.util.List;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.orderservice.vo.OrderRequest;
import com.sonal.istio.springboot.orderservice.vo.OrderResponse;

import brave.Span;
import brave.Tracer;
import brave.Tracing;
import brave.baggage.BaggageField;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Service
public class LogOrderService {

	private Tracer tracer;
	
	private Tracing tracing;
	
	private BaggageField orderServicePayload;
	
	@NewSpan(name = "logOrderV1")
	public Mono<ResponseEntity<OrderResponse>> logOrderV1(@SpanTag("orderRequest") OrderRequest orderRequest){
		
		orderServicePayload.updateValue(orderRequest.toString());
		
		log.info("************************ logOrderV1 ****************** " + orderRequest);
		
		return Mono.just(ResponseEntity.ok(OrderResponse.builder()
				  .message("Success")
				  .build()));
			
	}
	
	
	public Mono<ResponseEntity<OrderResponse>> logOrderV2(OrderRequest orderRequest){
		
		log.info("************************ logOrderV2 ****************** " + orderRequest);
		
		
		log.info("Original_Span going on");
		
		
        Span newLogOrderRequestSpan = tracer.nextSpan()
        									.name("New LogOrderRequest_Span")
        									.start();
        
        tracer.currentSpan().context().toBuilder().addExtra(orderRequest);
        

        try(Tracer.SpanInScope span = tracer.withSpanInScope(newLogOrderRequestSpan.start())) {
        	
        	orderServicePayload.updateValue(orderRequest.toString());
        	
        	newLogOrderRequestSpan.tag("order-service-payload", orderRequest.toString());
        	
        	
        	newLogOrderRequestSpan.annotate(Instant.now().toEpochMilli(), "OrderRequest Logged");
        	
        	
        	log.info("Logging OrderRequest In New LogOrderRequest_Span " + orderRequest );
        	
        }finally {
            newLogOrderRequestSpan.finish();
        }

        log.info("Back to Original_Span");
        
       // List<Object> extra = tracer.currentSpan().context().extra();
		
		return Mono.just(ResponseEntity.ok(OrderResponse.builder()
				  .message("Success")
				  .build()));
			
	}
}
