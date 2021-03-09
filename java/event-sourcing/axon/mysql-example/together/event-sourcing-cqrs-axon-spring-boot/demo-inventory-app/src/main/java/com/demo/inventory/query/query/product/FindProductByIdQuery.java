package com.demo.inventory.query.query.product;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FindProductByIdQuery {

	public final String productId; 
}
