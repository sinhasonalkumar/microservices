package com.demo.inventory.querymodule.entities.handlers.queryhandler;


import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.demo.inventory.querymodule.entities.ProductQueryEntity;
import com.demo.inventory.querymodule.entities.repositories.ProductRepository;
import com.demo.inventory.querymodule.query.product.FindProductByIdQuery;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductQueryHandler {

	private ProductRepository productRepository;

	@QueryHandler
	public ProductQueryEntity onFindProductByIdQuery(FindProductByIdQuery findProductByIdQuery) {
		return productRepository.findById(findProductByIdQuery.productId).get();
	}
	
	@QueryHandler(queryName = "listAllProducts")
    public Iterable<ProductQueryEntity> onListAllProducts() {
        return productRepository.findAll();
    }
}
