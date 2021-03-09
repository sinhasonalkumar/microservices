package com.demo.inventory.query.services.queries;


import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.demo.inventory.query.entities.ProductQueryEntity;

import reactor.core.publisher.Flux;

public interface ProductQueryService {
	
	Flux<ProductQueryEntity> listAllProducts();
	List<Object> listProductEvents(String productId);
    CompletableFuture<ProductQueryEntity> findProductById(String productId);
	void replayEvents(String name);
}
