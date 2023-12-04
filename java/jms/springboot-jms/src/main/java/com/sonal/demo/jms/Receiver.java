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
	public void receiveMethod1(String message, @Header("priority") String priority) {
		LOGGER.info("******** message RECEIVED={} of {} by receiveMethod1", message, priority);
		latch.countDown();
	}

	@JmsListener(destination = "${queue.name}", selector = "${receiver.selectorLow}")
	public void receiveMethod2(String message, @Header("priority") String priority) {
		LOGGER.info("******** message RECEIVED={} of {} by receiveMethod2", message, priority);
		latch.countDown();
	}
}
