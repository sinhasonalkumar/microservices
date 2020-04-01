package com.demo.perf.controllers.command;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.perf.dto.commands.CapabilityCreateDTO;
import com.demo.perf.dto.commands.CapabilityUpdateDTO;
import com.demo.perf.services.commands.CapabilityCommandService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/Capabilities")
@AllArgsConstructor
@Api(value = "Capability Commands", description = "Capability Commands Related Endpoints", tags = "Capability Commands")
public class CapabilityCommandController {

    private final CapabilityCommandService capabilityCommandService;
  
    @PostMapping(path = "/create")
    public CompletableFuture<String> createCapability(@RequestBody CapabilityCreateDTO capabilityCreateDTO){
        return capabilityCommandService.createCapability(capabilityCreateDTO);
    }
    
    @PutMapping(path = "/update/{capabilityId}")
    public CompletableFuture<String> updateCapability(@RequestBody CapabilityUpdateDTO capabilityUpdateDTO,@PathVariable(value = "capabilityId") String capabilityId){
        return capabilityCommandService.updateCapability(capabilityUpdateDTO,capabilityId);
    }
    
    @DeleteMapping(path = "/delete/{capabilityId}")
    public CompletableFuture<String> deleteCapability(@PathVariable(value = "capabilityId") String capabilityId){
        return capabilityCommandService.deleteCapability(capabilityId);
    }
}
