package com.sonal.demo.istio.userskillsratingsservice.service.vo;

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
