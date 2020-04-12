package com.sonal.demo.istio.apigateway.externalservice.restclient.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Capability implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3824587559394064009L;

	private String capabilityId;
	
	private String foreignSoucedCapabilityId;
	
	private String skillName;
}
