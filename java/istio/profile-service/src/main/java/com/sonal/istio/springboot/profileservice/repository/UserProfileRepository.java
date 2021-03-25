package com.sonal.istio.springboot.profileservice.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sonal.istio.springboot.profileservice.persistence.bo.UserProfileBO;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Repository
public class UserProfileRepository {

	private static final Map<String, UserProfileBO> userProfileStore = init();

	private static Map<String, UserProfileBO> init() {
		
		Map<String, UserProfileBO> allPayments = new HashMap<String, UserProfileBO>();
		
		allPayments.put("user1", UserProfileBO.builder()
											 .userId("user1")
											 .accountId("account1")
											 .firstName("Sachin")
											 .lastName("Tendulkar")
											 .address("101 Mumbai")
											 .emailId("sachin@bcci.com")
											 .build());
		
		allPayments.put("user2", UserProfileBO.builder()
											 .userId("user2")
											 .accountId("account2")
											 .firstName("Mahendra Singh")
											 .lastName("Dhoni")
											 .address("202 Ranchi")
											 .emailId("dhoni@bcci.com")
											 .build());
		
		
		allPayments.put("user3", UserProfileBO.builder()
											.userId("user3")
											.accountId("account3")
											.firstName("Virat")
											.lastName("Kohli")
											.address("304 Delhi")
											.emailId("virat@bcci.com")
											.build());
		
		
		return allPayments;
	}
	
	
	public Mono<UserProfileBO> findByUserId(String userId) {
		
		UserProfileBO userProfile = userProfileStore.get(userId);
		
		log.info("UserProfile For User : " + userId + " is " + userProfile);
		
		return Mono.just(userProfile);
	}
	
	
	
}
