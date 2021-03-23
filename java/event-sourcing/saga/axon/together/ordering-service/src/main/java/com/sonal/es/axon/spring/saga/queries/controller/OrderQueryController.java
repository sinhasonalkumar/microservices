package com.sonal.es.axon.spring.saga.queries.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.es.axon.spring.saga.queries.entity.OrderBO;
import com.sonal.es.axon.spring.saga.queries.service.OrderQueryService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderQueryController {

	private OrderQueryService orderQueryService;
	
	
	@GetMapping("/{orderId}")
    public CompletableFuture<OrderBO> getOrder(@PathVariable(value = "orderId") String orderId){
        return orderQueryService.findByOrderId(orderId);
    }
    
    @GetMapping(value = "/list",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<OrderBO> listAllOrders(){
        return orderQueryService.listAllOrders();
    }
	
}
