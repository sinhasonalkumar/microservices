package com.demo.perf.dto.commands;

import java.io.Serializable;

import lombok.Data;

@Data
public class SkillItemUpdateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4292677839421034820L;

	private String id;

    private String name;

    private String description;

}
