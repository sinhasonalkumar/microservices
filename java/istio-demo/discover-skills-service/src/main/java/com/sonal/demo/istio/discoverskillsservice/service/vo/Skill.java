package com.sonal.demo.istio.discoverskillsservice.service.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Skill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6722802523938746015L;

	
	private String id;
	
	private String skillName;
	
}
