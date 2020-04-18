package com.sonal.kafka.demo.trucklocationtracker.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sonal.kafka.demo.trucklocationtracker.event.TruckLocatedEvent;
import com.sonal.kafka.demo.trucklocationtracker.eventlistener.TruckLocatedEventListener;
import com.sonal.kafka.demo.trucklocationtracker.eventprocessor.TruckLocatedEventProcessor;

import reactor.core.publisher.Flux;

@RequestMapping("/truck")
@RestController
public class TruckLocatorController {

	@Autowired
    private TruckLocatedEventProcessor processor;    

    public TruckLocatorController() {
        
    }
    
    @GetMapping(value = "/track", produces = "text/event-stream;charset=UTF-8")
    public Flux<TruckLocatedEvent> getWeatherInfo() {
         return createTruckLocationEventStream().log();
    }

    private Flux<TruckLocatedEvent> createTruckLocationEventStream() {
         Flux<TruckLocatedEvent> truckLocatedEventStream = Flux.create(sink -> { 
              processor.register(new TruckLocatedEventListener() {

                  @Override
                  public void processComplete() {
                      sink.complete();
                  }

                  @Override
                  public void onData(TruckLocatedEvent data) {
                      sink.next(data);
                  }
               });
         });
         return truckLocatedEventStream;
    }
}
