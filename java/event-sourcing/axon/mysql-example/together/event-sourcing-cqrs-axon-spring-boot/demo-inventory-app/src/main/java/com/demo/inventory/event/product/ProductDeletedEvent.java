package com.demo.inventory.event.product;


import com.demo.inventory.event.BaseEvent;

import lombok.ToString;

@ToString(includeFieldNames = true)
public class ProductDeletedEvent extends BaseEvent<String> {
	
    public ProductDeletedEvent(String id) {
    	super(id);
    }

}
