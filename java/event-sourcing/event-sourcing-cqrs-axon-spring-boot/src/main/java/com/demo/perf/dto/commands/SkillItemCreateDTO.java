package com.demo.perf.dto.commands;

import java.io.Serializable;

import lombok.Data;

@Data
public class SkillItemCreateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String name;

    private String description;
}
