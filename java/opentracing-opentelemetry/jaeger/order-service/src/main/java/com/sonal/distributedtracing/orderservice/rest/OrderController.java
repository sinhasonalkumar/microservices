package com.sonal.distributedtracing.orderservice.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.distributedtracing.orderservice.annotation.LogTimeElapsed;
import com.sonal.distributedtracing.orderservice.service.OrderService;
import com.sonal.distributedtracing.orderservice.vo.OrderRequestVO;
import com.sonal.distributedtracing.orderservice.vo.OrderResponseVO;

import brave.Tracer;
import brave.baggage.BaggageField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private Tracer tracer;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BaggageField productIdField;

	@LogTimeElapsed
	@PostMapping
	public ResponseEntity<OrderResponseVO> placeOrder(@RequestBody OrderRequestVO orderRequestVO) {

		updateBaggagev2(orderRequestVO);

		productIdField.updateValue(orderRequestVO.getProductId());

		Map<String, String> allValues = BaggageField.getAllValues();

		log.debug("Active Trace : " + tracer.currentSpan().context().traceIdString());
		log.debug("Active Span : " + tracer.currentSpan().context().spanIdString());

		ResponseEntity<OrderResponseVO> response = ResponseEntity.ok(orderService.placeOrder(orderRequestVO));

		return response;
	}
	
	@LogTimeElapsed
	private void updateBaggagev2(OrderRequestVO orderRequestVO) {

		productIdField.updateValue(orderRequestVO.getProductId());
	}

	@LogTimeElapsed
	private void updateBaggagev1(OrderRequestVO orderRequestVO) {

		//		BaggagePropagation.newFactoryBuilder(B3Propagation.FACTORY)
		//		  .add(BaggagePropagationConfig.SingleBaggageField.remote(BaggageField.create("productId")))
		//		  .build();
		
		String baggageKey = "productId";
		String baggageValue = "cricket bat";
		BaggageField baggageField = BaggageField.create(baggageKey);
		baggageField.updateValue(baggageValue);
		//MDC.put(baggageKey, baggageValue);
	}
}
