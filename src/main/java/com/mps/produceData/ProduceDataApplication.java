package com.mps.produceData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ProduceDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProduceDataApplication.class, args);
	}

}
