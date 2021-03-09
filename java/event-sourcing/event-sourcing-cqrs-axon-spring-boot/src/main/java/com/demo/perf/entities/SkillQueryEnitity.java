package com.demo.perf.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillQueryEnitity {

	@Id
	private String id;

	private String capabilityId;

	private String name;

	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "skill_id")
	private List<SkillItemQueryEnitity> skillItems;
	
	public SkillQueryEnitity(String id) {
		this.id = id;
	}

}
