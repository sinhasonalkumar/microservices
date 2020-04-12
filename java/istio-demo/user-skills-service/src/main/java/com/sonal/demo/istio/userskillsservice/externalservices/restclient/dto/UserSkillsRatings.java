package com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSkillsRatings implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1478776085802355740L;
	
	private String userId;
	
	private List<SkillsRating> skillsRatings;

}
