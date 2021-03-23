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
public class ShipmentBO {

	@Id
    private String shipmentId;

	private String orderId;
	
	private Instant registeredOn;

	private String destination;
	
	private String status;
}
