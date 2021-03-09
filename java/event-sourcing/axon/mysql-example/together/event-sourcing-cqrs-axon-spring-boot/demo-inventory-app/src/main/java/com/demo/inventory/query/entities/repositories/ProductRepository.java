package com.demo.inventory.query.entities.repositories;


import org.springframework.data.repository.CrudRepository;

import com.demo.inventory.query.entities.ProductQueryEntity;

public interface ProductRepository extends CrudRepository<ProductQueryEntity, String> {
}
