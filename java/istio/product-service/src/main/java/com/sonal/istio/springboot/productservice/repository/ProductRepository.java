package com.sonal.istio.springboot.productservice.repository;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sonal.istio.springboot.productservice.persistence.bo.ProductBO;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Log4j2
@Repository
public class ProductRepository {

	private static final Map<String, ProductBO> productStore = init();

	private static Map<String, ProductBO> init() {
		
		Map<String, ProductBO> allPayments = new HashMap<String, ProductBO>();
		
		allPayments.put("product1", ProductBO.builder()
											 .productId("product1")
											 .name("SS Cricket Bat")
											 .description("SS English Willow Grade1 Plus Limitied Edition")
											 .cost(699.99f)
											 .build());
		
		allPayments.put("product2", ProductBO.builder()
											 .productId("product1")
											 .name("SG Cricket Bat")
											 .description("SG English Willow Grade1 Plus Limitied Edition")
											 .cost(599.99f)
											 .build());
		
		
		allPayments.put("product3", ProductBO.builder()
											 .productId("product1")
											 .name("TON Cricket Bat")
											 .description("TON English Willow Grade1 Plus Limitied Edition")
											 .cost(799.99f)
											 .build());
		
		
		return allPayments;
	}
	
	
	public Mono<ProductBO> findByProductId(String accountId) {
		
		ProductBO product = productStore.get(accountId);
		
		String message = MessageFormat.format("Fetch Product : {0}", product);
		
		log.info(message);
		
		return Mono.just(product);
	}
	
	
	
}
