package com.sonal.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.sonal.demo.jms.Priority;
import com.sonal.demo.jms.Receiver;
import com.sonal.demo.jms.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class DemoJMSApplicationTest {

	@Value("${queue.name}")
	private String queueName;

	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Test
	public void testReceive() throws Exception {
		sender.send(queueName, "This message is of priority", Priority.HIGH);
		sender.send(queueName, "This message is of priority", Priority.MEDIUM);
		sender.send(queueName, "This message is of priority", Priority.LOW);

		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}
}
