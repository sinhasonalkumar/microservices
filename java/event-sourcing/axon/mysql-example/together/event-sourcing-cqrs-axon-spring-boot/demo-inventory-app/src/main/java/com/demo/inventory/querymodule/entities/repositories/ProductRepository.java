package com.demo.inventory.querymodule.entities.repositories;


import org.springframework.data.repository.CrudRepository;

import com.demo.inventory.querymodule.entities.ProductQueryEntity;

public interface ProductRepository extends CrudRepository<ProductQueryEntity, String> {
}
