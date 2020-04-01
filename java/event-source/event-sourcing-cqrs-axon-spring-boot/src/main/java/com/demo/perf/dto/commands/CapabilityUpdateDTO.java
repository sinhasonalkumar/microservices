package com.demo.perf.dto.commands;

import java.io.Serializable;

import com.demo.perf.aggregates.capability.CapabilityType;

import lombok.Data;

@Data
public class CapabilityUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6511409302120720317L;
	
	private String name;

    private String description;
    
    private  CapabilityType capabilityType;
    
}
