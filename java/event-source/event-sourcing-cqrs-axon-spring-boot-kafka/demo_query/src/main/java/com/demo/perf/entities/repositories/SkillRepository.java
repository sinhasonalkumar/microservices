package com.demo.perf.entities.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.perf.entities.SkillQueryEnitity;

public interface SkillRepository extends CrudRepository<SkillQueryEnitity, String> {

}
