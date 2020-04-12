package com.sonal.demo.istio.capabilityservice.externalservices.restclient.dto;


import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SkillsDiscoveredDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3598145858481454084L;
	
	private List<Skill> skillsDiscovered;
}
