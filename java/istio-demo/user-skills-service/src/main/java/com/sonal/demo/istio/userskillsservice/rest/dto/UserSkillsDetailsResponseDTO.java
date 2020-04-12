package com.sonal.demo.istio.userskillsservice.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.sonal.demo.istio.userskillsservice.service.vo.UserSkillsDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSkillsDetailsResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1266753206055682376L;
	
	private List<UserSkillsDetails> userSkillsDetails;
}
