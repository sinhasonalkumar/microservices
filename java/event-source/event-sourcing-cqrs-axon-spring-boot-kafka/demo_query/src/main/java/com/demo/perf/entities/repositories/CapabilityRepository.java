package com.demo.perf.entities.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.perf.entities.CapabilityQueryEntity;

public interface CapabilityRepository extends CrudRepository<CapabilityQueryEntity, String> {
}
