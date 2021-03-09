package com.demo.inventory.events.product;


import com.demo.inventory.events.BaseEvent;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class ProductDeletedEvent extends BaseEvent<String> {
	
    public ProductDeletedEvent(String id) {
    	super(id);
    }

}
