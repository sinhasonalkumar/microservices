package com.sonal.kafka.demo.trucklocationtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TruckLocationProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruckLocationProducerApplication.class, args);
	}

}
