package com.demo.inventory.command.aggregates.product;

import java.io.Serializable;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.inventory.command.commands.product.CreateProductCommand;
import com.demo.inventory.command.commands.product.DeleteProductCommand;
import com.demo.inventory.command.commands.product.UpdateProductCommand;
import com.demo.inventory.event.product.ProductCreatedEvent;
import com.demo.inventory.event.product.ProductDeletedEvent;
import com.demo.inventory.event.product.ProductUpdatedEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Aggregate(snapshotTriggerDefinition = "inventorySnapshotTriggerDefinition")
@Data
@NoArgsConstructor
public class ProductAggregate implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5371008864652016669L;
	
	private Logger logger = LoggerFactory.getLogger(ProductAggregate.class);

	@AggregateIdentifier
    private String id;

    private String name;

    private String description;
    
    private ProductType productType;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand){
    	logger.info(" @CommandHandler :: CreateProductCommand " + createProductCommand);
    	AggregateLifecycle.apply(new ProductCreatedEvent(createProductCommand.id, createProductCommand.name, createProductCommand.description, createProductCommand.productType));
    }
    
    @CommandHandler
    public void on(UpdateProductCommand updateProductCommand){
    	logger.info("  @CommandHandler :: UpdateProductCommand : " + updateProductCommand);
    	AggregateLifecycle.apply(new ProductUpdatedEvent(updateProductCommand.id, updateProductCommand.name, updateProductCommand.description , updateProductCommand.productType));
    }
    
    @CommandHandler
    public void on(DeleteProductCommand deleteProductCommand){
    	logger.info(" @CommandHandler :: DeleteProductCommand : " + deleteProductCommand);
    	AggregateLifecycle.apply(new ProductDeletedEvent(deleteProductCommand.id));
    }
    

    @EventSourcingHandler
    protected void on(ProductCreatedEvent ProductCreatedEvent){
    	logger.info("@EventSourcingHandler :: ProductCreatedEvent : " + ProductCreatedEvent);
    	this.id = ProductCreatedEvent.id;
        this.name = ProductCreatedEvent.name;
        this.description = ProductCreatedEvent.description;
    }
    
    @EventSourcingHandler
    protected void on(ProductUpdatedEvent ProductUpdatedEvent){
    	logger.info("@EventSourcingHandler :: ProductUpdatedEvent : " + ProductUpdatedEvent);
    	this.id = ProductUpdatedEvent.id;
        this.name = ProductUpdatedEvent.name;
        this.description = ProductUpdatedEvent.description;
    }
    
    @EventSourcingHandler
    protected void on(ProductDeletedEvent ProductUpdatedEvent){
    	logger.info("@EventSourcingHandler :: ProductDeletedEvent : " + ProductUpdatedEvent);
    	this.id = ProductUpdatedEvent.id;
    }
    

    
    
}
