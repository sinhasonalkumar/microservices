package com.sonal.demo.istio.discoverskillsservice.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.sonal.demo.istio.discoverskillsservice.service.vo.Skill;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SkillsDiscoveredDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3598145858481454084L;
	
	private List<Skill> skillsDiscovered;
}
