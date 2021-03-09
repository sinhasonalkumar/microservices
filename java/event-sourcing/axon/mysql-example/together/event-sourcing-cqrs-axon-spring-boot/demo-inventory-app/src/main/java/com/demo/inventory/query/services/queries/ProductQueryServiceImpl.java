package com.demo.inventory.query.services.queries;


import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryBackpressure;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.stereotype.Service;

import com.demo.inventory.query.entities.ProductQueryEntity;
import com.demo.inventory.query.query.product.FindProductByIdQuery;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

	private final EventStore eventStore;

	private final QueryGateway queryGateway;
	
	private final EventProcessingConfiguration eventProcessingConfiguration;

	@Override
	public List<Object> listProductEvents(String productId) {
		return eventStore.readEvents(productId).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}

	@Override
	public CompletableFuture<ProductQueryEntity> findProductById(String productId) {
		return queryGateway.query(new FindProductByIdQuery(productId), ProductQueryEntity.class);
	}
	
	@Override
	public Flux<ProductQueryEntity> listAllProducts() {
		 SubscriptionQueryResult<List<ProductQueryEntity>, ProductQueryEntity> response = queryGateway.subscriptionQuery("listAllProducts",null,
												ResponseTypes.multipleInstancesOf(ProductQueryEntity.class),
												ResponseTypes.instanceOf(ProductQueryEntity.class),
											    SubscriptionQueryBackpressure.defaultBackpressure());
		 return response.initialResult()
				 		.flatMapMany(Flux::fromIterable)
				 		.concatWith(response.updates());
	}

	@Override
	public void replayEvents(String name) {
		eventProcessingConfiguration.eventProcessor(name, TrackingEventProcessor.class).ifPresent(processor -> {
			processor.shutDown();
			processor.resetTokens();
			processor.start();
		});
	}

}
