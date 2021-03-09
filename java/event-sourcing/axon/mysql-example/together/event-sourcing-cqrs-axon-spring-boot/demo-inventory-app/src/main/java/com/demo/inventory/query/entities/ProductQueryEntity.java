package com.demo.inventory.query.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.demo.inventory.command.aggregates.product.ProductType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueryEntity {

    @Id
    private String id;
    
    private String name;

    private String description;
    
    private ProductType productType;
    
    public ProductQueryEntity(String id) {
    	this.id = id;
    }
}
