package com.demo.inventory.command.controller;


import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventory.command.dto.commands.CreateProductDTO;
import com.demo.inventory.command.dto.commands.UpdateProductDTO;
import com.demo.inventory.command.service.commands.ProductCommandService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@AllArgsConstructor
@Api(value = "Inventory Commands", tags = "Inventory Commands")
public class ProductCommandController {

    private final ProductCommandService productCommandService;
  
    @PostMapping(path = "/product")
    public CompletableFuture<String> createProduct(@RequestBody CreateProductDTO createProductDTO){
        return productCommandService.createProduct(createProductDTO);
    }
    
    @PutMapping(path = "/product/{productId}")
    public CompletableFuture<String> updateProduct(@RequestBody UpdateProductDTO updateProductDTO,@PathVariable(value = "productId") String productId){
        return productCommandService.updateProduct(updateProductDTO,productId);
    }
    
    @DeleteMapping(path = "/product/{productId}")
    public CompletableFuture<String> deleteProduct(@PathVariable(value = "productId") String productId){
        return productCommandService.deleteProduct(productId);
    }
}
