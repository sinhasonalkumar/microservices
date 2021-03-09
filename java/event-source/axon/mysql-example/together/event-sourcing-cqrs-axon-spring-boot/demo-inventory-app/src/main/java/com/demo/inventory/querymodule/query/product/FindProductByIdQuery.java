package com.demo.inventory.querymodule.query.product;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FindProductByIdQuery {

	public final String productId; 
}
