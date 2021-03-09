package com.demo.inventory.events.product;


import com.demo.inventory.commandmodule.aggregates.product.ProductType;
import com.demo.inventory.events.BaseEvent;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class ProductCreatedEvent extends BaseEvent<String> {

	public final String name;

	public final String description;

	public final ProductType productType;

	public ProductCreatedEvent(String id, String name, String description, ProductType productType) {
		super(id);
		this.description = description;
		this.name = name;
		this.productType = productType;
	}

}
