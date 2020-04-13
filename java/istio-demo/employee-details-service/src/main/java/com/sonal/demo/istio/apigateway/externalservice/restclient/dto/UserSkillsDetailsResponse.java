package com.sonal.demo.istio.apigateway.externalservice.restclient.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSkillsDetailsResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1266753206055682376L;
	
	private List<UserSkillsDetails> userSkillsDetails;
}
