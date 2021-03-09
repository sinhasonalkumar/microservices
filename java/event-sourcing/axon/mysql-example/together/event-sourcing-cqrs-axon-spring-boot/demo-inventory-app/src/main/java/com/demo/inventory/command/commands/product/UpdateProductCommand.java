package com.demo.inventory.command.commands.product;


import com.demo.inventory.command.aggregates.product.ProductType;
import com.demo.inventory.command.commands.BaseCommand;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class UpdateProductCommand extends BaseCommand<String> {

    public final String name;

    public final String description;

    public final ProductType productType;

    public UpdateProductCommand(String id, String name, String description,ProductType productType) {
    	super(id);
        this.description = description;
        this.name = name;
        this.productType = productType;
    }

}
