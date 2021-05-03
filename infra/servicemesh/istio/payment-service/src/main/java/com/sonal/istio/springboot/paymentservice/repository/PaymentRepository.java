package com.sonal.istio.springboot.paymentservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sonal.istio.springboot.paymentservice.persistence.bo.PaymentBO;

import reactor.core.publisher.Mono;

@Repository
public class PaymentRepository {

	private static final Map<String, PaymentBO> paymentStore = init();

	private static Map<String, PaymentBO> init() {
		
		Map<String, PaymentBO> allPayments = new HashMap<String, PaymentBO>();
		
		allPayments.put("account1", PaymentBO.builder()
											 .accountId("account1")
											 .ccNumber("*****0101")
											 .token("**01")
											 .build());
		
		allPayments.put("account2", PaymentBO.builder()
											 .accountId("account2") 
											 .ccNumber("*****0202")
											 .token("**02")
											 .build());
		
		
		allPayments.put("account3", PaymentBO.builder()
											 .accountId("account3") 
											 .ccNumber("*****0303")
											 .token("**03")
											 .build());
		
		
		return allPayments;
	}
	
	
	public Mono<PaymentBO> findByAccountId(String accountId) {
		return Mono.just(paymentStore.get(accountId));
	}
	
	
	
}
