package com.demo.perf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
public class DemoESAxonSpringBootCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoESAxonSpringBootCommandApplication.class, args);
	}

}

