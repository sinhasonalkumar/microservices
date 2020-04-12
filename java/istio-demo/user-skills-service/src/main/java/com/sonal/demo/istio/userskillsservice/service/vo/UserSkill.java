package com.sonal.demo.istio.userskillsservice.service.vo;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSkill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6722802523938746015L;

	
	private String capabilityId;
	
}
