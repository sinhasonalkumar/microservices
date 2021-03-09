package com.demo.perf.dto.commands;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class SkillCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5040641203183731279L;
	
	private String capabilityId;

    private String name;

    private String description;
    
    private List<SkillItemCreateDTO> skillItems;
}
