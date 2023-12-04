package com.sonal.demo.jms;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

	private CountDownLatch latch = new CountDownLatch(3);

	public CountDownLatch getLatch() {
		return latch;
	}

    @JmsListener(destination = "${queue.name}", selector = "${receiver.selectorHighMedium}")
	public void receiveHigh(String message, @Header("priority") String priority) {
    	LOGGER.info("******** message RECEIVED={} of {}", message, priority);
		latch.countDown();
	}

	@JmsListener(destination = "${queue.name}", selector = "${receiver.selectorLow}")
	public void receiveLow(String message,@Header("priority") String priority) {
		LOGGER.info("******** message RECEIVED={} of {}", message, priority);
		latch.countDown();
	}
}
