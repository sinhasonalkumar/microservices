package com.sonal.demo.istio.userskillsservice.externalservices.restclient.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SkillsRating implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3396077731237875828L;

	private String capabilityId;
	
	private String rating;

}
