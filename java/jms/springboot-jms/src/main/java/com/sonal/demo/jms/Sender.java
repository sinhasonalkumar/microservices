package com.sonal.demo.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String destination, String message, Priority priority) {
		LOGGER.info("######## message SENT={} {}", message, priority);

		jmsTemplate.convertAndSend(destination, message, messagePostProcessor -> {
			messagePostProcessor.setStringProperty("priority", priority.name());
			return messagePostProcessor;
		});
	}
}
