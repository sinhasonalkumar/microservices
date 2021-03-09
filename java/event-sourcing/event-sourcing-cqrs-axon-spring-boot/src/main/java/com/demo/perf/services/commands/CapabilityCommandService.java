package com.demo.perf.services.commands;

import java.util.concurrent.CompletableFuture;

import com.demo.perf.dto.commands.CapabilityCreateDTO;
import com.demo.perf.dto.commands.CapabilityUpdateDTO;

public interface CapabilityCommandService {

    CompletableFuture<String> createCapability(CapabilityCreateDTO CapabilityCreateDTO);
    
    CompletableFuture<String> updateCapability(CapabilityUpdateDTO capabilityUpdateDTO,String capabilityId);
    
    CompletableFuture<String> deleteCapability(String capabilityId);
  
}
