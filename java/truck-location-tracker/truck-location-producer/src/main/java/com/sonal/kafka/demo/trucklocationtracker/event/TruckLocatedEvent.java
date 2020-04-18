package com.sonal.kafka.demo.trucklocationtracker.event;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TruckLocatedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5939870669138609660L;
	
	private String truckId;
	
	private double longitude;
	
	private double latitude;

}
