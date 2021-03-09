package com.demo.inventory.query.entities.handlers.eventhandler;


import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import com.demo.inventory.event.product.ProductCreatedEvent;
import com.demo.inventory.event.product.ProductDeletedEvent;
import com.demo.inventory.event.product.ProductUpdatedEvent;
import com.demo.inventory.query.entities.ProductQueryEntity;
import com.demo.inventory.query.entities.repositories.ProductRepository;

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
