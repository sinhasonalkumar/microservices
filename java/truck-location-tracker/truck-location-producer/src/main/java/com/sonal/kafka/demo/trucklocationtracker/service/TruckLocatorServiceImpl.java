package com.sonal.kafka.demo.trucklocationtracker.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import com.sonal.kafka.demo.trucklocationtracker.event.TruckLocatedEvent;

@Service
public class TruckLocatorServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KafkaTemplate<String, TruckLocatedEvent> kafkaTemplate;

	public ListenableFuture<SendResult<String, TruckLocatedEvent>> sendTruckLocationMessage(String topic, TruckLocatedEvent message) {

		logger.info(String.format("TruckLocatedEvent ******* -> Emitting TruckLocated Event Message -> %s", message));

		return this.kafkaTemplate.send(topic, message);
	}

	@Scheduled(fixedDelay = 3000)
	public void getRandomLocationGeneratJob() throws IOException {

		TruckLocatedEvent truckLocatedEvent = generateRandomTruckLocation();

		logger.info("******* generated random Location event *******");

		sendTruckLocationMessage("truckLocatorTopic", truckLocatedEvent);

	}

	private TruckLocatedEvent generateRandomTruckLocation() {
		double longitude = Math.random() * Math.PI * 2;
		double latitude = Math.acos(Math.random() * 2 - 1);

		TruckLocatedEvent truckLocatedEvent = new TruckLocatedEvent("1", longitude, latitude);
		return truckLocatedEvent;
	}

}
