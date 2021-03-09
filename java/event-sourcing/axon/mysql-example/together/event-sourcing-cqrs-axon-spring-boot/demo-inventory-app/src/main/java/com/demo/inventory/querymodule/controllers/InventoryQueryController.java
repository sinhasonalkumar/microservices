package com.demo.inventory.querymodule.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.inventory.querymodule.entities.ProductQueryEntity;
import com.demo.inventory.querymodule.services.queries.ProductQueryService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/v1/inventory")
@Api(value = "Inventory Queries", tags = "Inventory Queries")
@AllArgsConstructor
public class InventoryQueryController {

    private final ProductQueryService productQueryService;

    @GetMapping("/product/{productId}")
    public CompletableFuture<ProductQueryEntity> getProduct(@PathVariable(value = "productId") String productId){
        return productQueryService.findProductById(productId);
    }
    
    @GetMapping(value = "/product/list",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductQueryEntity> listAllProducts(){
        return productQueryService.listAllProducts();
    }

    @GetMapping("product/{productId}/events")
    public List<Object> listProductEvents(@PathVariable(value = "productId") String productId){
        return productQueryService.listProductEvents(productId);
    }
    
    @PutMapping("product/replayEvents")
	public void replayEvents() {
    	productQueryService.replayEvents("com.demo.inventory.querymodule.entities.handlers.eventhandler");
	}
    
}
