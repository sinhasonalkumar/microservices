package com.demo.inventory.command.service.commands;


import java.util.concurrent.CompletableFuture;

import com.demo.inventory.command.dto.commands.CreateProductDTO;
import com.demo.inventory.command.dto.commands.UpdateProductDTO;

public interface ProductCommandService {

    CompletableFuture<String> createProduct(CreateProductDTO productCreateDTO);
    
    CompletableFuture<String> updateProduct(UpdateProductDTO productUpdateDTO,String productId);
    
    CompletableFuture<String> deleteProduct(String productId);
  
}
