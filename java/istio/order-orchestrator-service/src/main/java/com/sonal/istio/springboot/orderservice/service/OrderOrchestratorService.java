package com.sonal.istio.springboot.orderservice.service;


import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.orderservice.client.NotificationServiceClient;
import com.sonal.istio.springboot.orderservice.client.PaymentServiceClient;
import com.sonal.istio.springboot.orderservice.client.ProductServiceClient;
import com.sonal.istio.springboot.orderservice.client.ProfileServiceClient;
import com.sonal.istio.springboot.orderservice.client.ShippingServiceClient;
import com.sonal.istio.springboot.orderservice.client.vo.ChainnedResponse;
import com.sonal.istio.springboot.orderservice.client.vo.ProductResponse;
import com.sonal.istio.springboot.orderservice.client.vo.UserProfileResponse;
import com.sonal.istio.springboot.orderservice.vo.OrderRequest;
import com.sonal.istio.springboot.orderservice.vo.OrderResponse;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@AllArgsConstructor
public class OrderOrchestratorService {
	
	private ProfileServiceClient profileServiceClient;
	
	private ProductServiceClient productServiceClient;
	
	private PaymentServiceClient paymentServiceClient;
	
	private ShippingServiceClient shippingServiceClient;
	
	private NotificationServiceClient notificationServiceClient;

	public Mono<OrderResponse> placeOrder(OrderRequest orderRequest) {
		
		
		Mono<UserProfileResponse> userProfileResponse = profileServiceClient.userProfile(orderRequest.getUserId());
		
		Mono<ProductResponse> productResponse = productServiceClient.product(orderRequest.getProductId());
	
		
		return  Mono.zip(userProfileResponse, productResponse)
					.log()
					.flatMap(zippedResp -> paymentServiceClient.postPayment(zippedResp.getT1(), zippedResp.getT2()))
					.log()
					.flatMap(chainnedResp -> shippingServiceClient.ship(chainnedResp.getUserProfileResponse(), chainnedResp.getProductResponse(), chainnedResp.getPaymentResponse(), orderRequest.getProductId()))
					.log()
					.flatMap(chainnedResponse -> notificationServiceClient.sendNotification(chainnedResponse))
					.log()
					.map(chainnedResponse -> buildOrderResponse(chainnedResponse));				
	}

	private OrderResponse buildOrderResponse(ChainnedResponse chainnedResponse) {
		
		String orderId = UUID.randomUUID().toString();
		
		OrderResponse orderResponse = OrderResponse.builder()
					  							   .orderId(orderId)
					  							   .message("Successfully placed order and Your order has been shipped")
					  							   .orderDetails(chainnedResponse.getProductResponse())
					  							   .build();
		log.info("Successfully placed order and Your order has been shipped. Order Deatils : " + orderResponse);
		
		return orderResponse;
	}
	
}
