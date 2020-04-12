package com.sonal.demo.istio.capabilityservice.externalservices.restclient.dto;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6722802523938746015L;

	
	private String id;
	
	private String skillName;
	
}
