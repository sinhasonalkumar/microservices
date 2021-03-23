package com.sonal.es.axon.spring.saga.queries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sonal.es.axon.spring.saga.queries.entity.OrderBO;

public interface OrderRepository extends JpaRepository<OrderBO, String> {

}
