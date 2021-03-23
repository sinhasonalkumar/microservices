package com.sonal.es.axon.spring.saga.queries.repository.queries.shipment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class FindOrderByIdQuery {

	private final String orderId; 
}
