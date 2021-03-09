package com.demo.inventory.querymodule.entities.handlers.eventhandler;


import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import com.demo.inventory.events.product.ProductCreatedEvent;
import com.demo.inventory.events.product.ProductDeletedEvent;
import com.demo.inventory.events.product.ProductUpdatedEvent;
import com.demo.inventory.querymodule.entities.ProductQueryEntity;
import com.demo.inventory.querymodule.entities.repositories.ProductRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductQueryEntityManager {
	
    
    private ProductRepository productRepository;
    
    private final QueryUpdateEmitter updateEmitter;
    
    @EventHandler
	public void on(ProductCreatedEvent productCreatedEvent) throws Exception {
    	ProductQueryEntity productQueryEntity = new ProductQueryEntity(productCreatedEvent.id,productCreatedEvent.name,productCreatedEvent.description,productCreatedEvent.productType);
    	  
    	productRepository.save(productQueryEntity);
    	updateEmitter.emit(m -> "listAllProducts".equals(m.getQueryName()), productQueryEntity);
	}
    
    @EventHandler
	public void on(ProductUpdatedEvent productUpdatedEvent) throws Exception {
       	 ProductQueryEntity productQueryEntity = new ProductQueryEntity(productUpdatedEvent.id,productUpdatedEvent.name,productUpdatedEvent.description, productUpdatedEvent.productType);
       
       	 productRepository.save(productQueryEntity);
       	 updateEmitter.emit(m -> "listAllProducts".equals(m.getQueryName()), productQueryEntity);
   	}
    
    @EventHandler
   	public void on(ProductDeletedEvent productDeletedEvent) throws Exception {
       	 ProductQueryEntity productQueryEntity = new ProductQueryEntity(productDeletedEvent.id);
     	
       	 productRepository.delete(productQueryEntity);
       	 updateEmitter.emit(m -> "listAllProducts".equals(m.getQueryName()), productQueryEntity);
   	}
    
    @ResetHandler
    public void resetTokensAction() {
    	productRepository.deleteAll();
    }
    
}
