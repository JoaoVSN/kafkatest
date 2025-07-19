package com.kafka.kafkatest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class KafkatestApplication {

	public static void main(String[] args) {

		SpringApplication.run(KafkatestApplication.class, args);
		log.info("The application its started");
	}

}
