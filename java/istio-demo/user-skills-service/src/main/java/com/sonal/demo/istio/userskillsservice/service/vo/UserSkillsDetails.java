package com.sonal.demo.istio.userskillsservice.service.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserSkillsDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2514143542048585691L;

	private String capabilityId;
	
	private String skillName;
	
	private String rating;
}
