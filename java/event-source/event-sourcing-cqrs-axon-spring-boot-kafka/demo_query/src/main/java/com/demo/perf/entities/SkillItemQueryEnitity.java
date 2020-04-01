package com.demo.perf.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillItemQueryEnitity {

	@Id
	private String id;

    private String name;

    private String description;
}
