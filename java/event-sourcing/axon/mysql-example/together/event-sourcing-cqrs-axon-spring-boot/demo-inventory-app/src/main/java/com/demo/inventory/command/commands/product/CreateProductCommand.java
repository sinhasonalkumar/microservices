package com.demo.inventory.command.commands.product;


import com.demo.inventory.command.aggregates.product.ProductType;
import com.demo.inventory.command.commands.BaseCommand;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class CreateProductCommand extends BaseCommand<String> {

    public final String name;

    public final String description;
    
    public final ProductType productType;
    
    public CreateProductCommand(String id, String name, String description, ProductType productType) {
        super(id);
        this.description = description;
        this.name = name;
        this.productType = productType;
    }
}
