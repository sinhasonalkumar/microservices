package com.sonal.oidc.spring.okta.productservice.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.sonal.oidc.spring.okta.productservice.persistence.bo.ProductBO;

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
	
	public List<ProductBO> findAll(){
		
		return productStore.entrySet()
						   .stream()
						   .map(e -> e.getValue())
						   .collect(Collectors.toList());
	}
}
