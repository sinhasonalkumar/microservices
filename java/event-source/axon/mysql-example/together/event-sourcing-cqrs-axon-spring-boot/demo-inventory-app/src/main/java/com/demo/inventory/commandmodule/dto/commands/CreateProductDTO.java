package com.demo.inventory.commandmodule.dto.commands;

import java.io.Serializable;

import com.demo.inventory.commandmodule.aggregates.product.ProductType;

import lombok.Data;

@Data
public class CreateProductDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8563653048861102700L;

	private String name;

    private String description;
    
    private  ProductType productType;
   
}
