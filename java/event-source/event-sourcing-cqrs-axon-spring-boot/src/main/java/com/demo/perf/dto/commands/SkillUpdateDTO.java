package com.demo.perf.dto.commands;

import java.util.List;

import lombok.Data;

@Data
public class SkillUpdateDTO {

	private String id;

	private String capabilityId;

	private String name;

	private String description;

	private List<SkillItemUpdateDTO> skillItems;
}
