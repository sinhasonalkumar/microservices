package com.sonal.kafka.demo.trucklocationtracker.eventprocessor;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.sonal.kafka.demo.trucklocationtracker.event.TruckLocatedEvent;
import com.sonal.kafka.demo.trucklocationtracker.eventlistener.TruckLocatedEventListener;

@Service
public class TruckLocatedEventProcessor {

	private final Logger logger = LoggerFactory.getLogger(getClass());
    private TruckLocatedEventListener listener;

    public void register(TruckLocatedEventListener listener) {
         this.listener = listener;
    }

    public void onEvent(TruckLocatedEvent event) {
         if (listener != null) {
              listener.onData(event);
         }
     }

    public void onComplete() {
        if (listener != null) {
             listener.processComplete();
        }
    }

    @KafkaListener(topics = "truckLocatorTopic", groupId = "truckLocatorConsumerGroup")
    public void consume(TruckLocatedEvent message) throws IOException {
         logger.info(String.format("******* -> Consumed message -> %s", message));
         onEvent(message);
    }
	
}
