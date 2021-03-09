package com.demo.inventory.command.dto.commands;


import java.io.Serializable;

import com.demo.inventory.command.aggregates.product.ProductType;

import lombok.Data;

@Data
public class UpdateProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6511409302120720317L;
	
	private String name;

    private String description;
    
    private  ProductType productType;
    
}
