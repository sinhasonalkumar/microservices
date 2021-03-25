package com.sonal.istio.springboot.paymentservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.paymentservice.persistence.bo.PaymentBO;
import com.sonal.istio.springboot.paymentservice.repository.PaymentRepository;
import com.sonal.istio.springboot.paymentservice.vo.PaymentResponse;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Service
@AllArgsConstructor
public class PaymentService {
	
	
	private PaymentRepository paymentRepository;

	public Mono<PaymentResponse> pay(String accountId, Float amount) {
		
		return paymentRepository.findByAccountId(accountId)
								.map(paymentBO -> doPayment(paymentBO, accountId, amount));
								
	}
	
	private PaymentResponse doPayment(PaymentBO paymentBO, String accountId, Float amount) {
		
		log.info("Charging Credit Card For Account " + accountId + " For amount " + amount + " ccInfo " + paymentBO);
		
		String confirmationNumber = UUID.randomUUID().toString();
		
		String message = "Transaction Successful with TransactionId " + confirmationNumber;
		log.info(message );
		
		
		return PaymentResponse.builder()
							  .confirmationNumber(confirmationNumber)
							  .message(message)
							  .build();
	}
	
}
