package com.sonal.istio.springboot.paymentservice.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sonal.istio.springboot.paymentservice.client.NotificationServiceClient;
import com.sonal.istio.springboot.paymentservice.client.ProfileServiceClient;
import com.sonal.istio.springboot.paymentservice.client.vo.ChainedResponse;
import com.sonal.istio.springboot.paymentservice.client.vo.NotificationRequest;
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
	
	private ProfileServiceClient profileServiceClient;
	
	private NotificationServiceClient notificationServiceClient;

	public Mono<PaymentResponse> pay(String accountId, Float amount) {
		
		
		
		return paymentRepository.findByAccountId(accountId)
								.map(paymentBO -> doPayment(paymentBO, accountId, amount))
								.flatMap(paymentResponse -> profileServiceClient.userProfile(accountId,paymentResponse))
								.flatMap(chainedRes -> notificationServiceClient.sendNotification(buildNotificationRequest(chainedRes), chainedRes))
								.map(chainedRes -> chainedRes.getPaymentResponse());
								
								
	}
	
	private NotificationRequest buildNotificationRequest(ChainedResponse chainedResponse) {
		
		return NotificationRequest.builder()
								  .email(chainedResponse.getUserProfileResponse().getEmailId())
								  .message(chainedResponse.getPaymentResponse().getMessage())
								  .build();
	}
	
	private PaymentResponse doPayment(PaymentBO paymentBO, String accountId, Float amount) {
		
		String message = "Charging Credit Card For Account " + accountId + " For amount " + amount + " ccInfo " + paymentBO;
		
		log.info(message);
		
		String confirmationNumber = UUID.randomUUID().toString();
		
		String finalMessage = "Transaction Successful with TransactionId " + confirmationNumber + " : " + message;
		log.info(finalMessage);
		
		
		return PaymentResponse.builder()
							  .confirmationNumber(confirmationNumber)
							  .message(finalMessage)
							  .build();
	}
	
}
