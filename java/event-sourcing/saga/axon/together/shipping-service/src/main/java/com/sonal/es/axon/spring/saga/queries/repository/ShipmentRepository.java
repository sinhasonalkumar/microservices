package com.sonal.es.axon.spring.saga.queries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonal.es.axon.spring.saga.queries.entity.ShipmentBO;

public interface ShipmentRepository extends JpaRepository<ShipmentBO, String> {

}
