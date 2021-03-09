package com.demo.perf.dto.commands;

import java.io.Serializable;

import com.demo.perf.aggregates.capability.CapabilityType;

import lombok.Data;

@Data
public class CapabilityCreateDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8563653048861102700L;

	private String name;

    private String description;
    
    private  CapabilityType capabilityType;
   
}
