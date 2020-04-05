package com.demo.perf.controllers.query;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.perf.entities.CapabilityQueryEntity;
import com.demo.perf.services.queries.CapabilityQueryService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/Capabilities")
@Api(value = "Capability Queries", description = "Capability Query Events Endpoint", tags = "Capability Queries")
@AllArgsConstructor
public class CapabilityQueryController {

    private final CapabilityQueryService capabilityQueryService;

    @GetMapping("/{capabilityId}")
    public CompletableFuture<CapabilityQueryEntity> getCapability(@PathVariable(value = "capabilityId") String capabilityId){
        return capabilityQueryService.getCapability(capabilityId);
    }
    
    @GetMapping(value = "/list",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CapabilityQueryEntity> listAllCapability(){
        return capabilityQueryService.listAllCapabilities();
    }

    @GetMapping("/{capabilityId}/events")
    public List<Object> listEventsForCapability(@PathVariable(value = "capabilityId") String capabilityId){
        return capabilityQueryService.listEventsForCapability(capabilityId);
    }
    
    @PutMapping("/replayEvents")
	public void replayEvents() {
    	capabilityQueryService.replayEvents("com.demo.perf.entities.handlers");
	}
    
}
