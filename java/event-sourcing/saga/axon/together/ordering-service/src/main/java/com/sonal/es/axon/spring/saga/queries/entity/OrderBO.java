package com.sonal.es.axon.spring.saga.queries.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderBO {
	
    @Id
    private String orderId;
	
    private String productId;
	
	private String productName;

	private String destinationAddress;	
	
	private String orderStatus;
	
	private String shipmentId;
	
	private Instant orderedOn;
	
}
