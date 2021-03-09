package com.demo.inventory.commandmodule.service.commands;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.demo.inventory.commandmodule.commands.product.CreateProductCommand;
import com.demo.inventory.commandmodule.commands.product.DeleteProductCommand;
import com.demo.inventory.commandmodule.commands.product.UpdateProductCommand;
import com.demo.inventory.commandmodule.dto.commands.CreateProductDTO;
import com.demo.inventory.commandmodule.dto.commands.UpdateProductDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductCommandServiceImpl implements ProductCommandService {

	private final CommandGateway commandGateway;

	@Override
	public CompletableFuture<String> createProduct(CreateProductDTO createProductDTO) {

		CreateProductCommand createProductCommand = new CreateProductCommand(UUID.randomUUID().toString(), 
																	  createProductDTO.getName(),
																	  createProductDTO.getDescription(), 
																	  createProductDTO.getProductType()
																	);
		CompletableFuture<String> response = commandGateway.send(createProductCommand);
		
		return response;
	}

	@Override
	public CompletableFuture<String> updateProduct(UpdateProductDTO updateProductDTO, String productId) {

		UpdateProductCommand updateProductCommand = new UpdateProductCommand(productId, 
																updateProductDTO.getName(),
																updateProductDTO.getDescription(), 
																updateProductDTO.getProductType()
															   );
		
		return commandGateway.send(updateProductCommand);
	}

	@Override
	public CompletableFuture<String> deleteProduct(String productId) {

		DeleteProductCommand command = new DeleteProductCommand(productId);
		
		return commandGateway.send(command);
	}

}