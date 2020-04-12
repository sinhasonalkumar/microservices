package com.sonal.demo.istio.userskillsratingsservice.service.vo;

import java.io.Serializable;
import java.util.List;

import com.sonal.demo.istio.userskillsratingsservice.service.vo.SkillsRating;

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
