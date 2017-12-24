package com.tebeshir.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitterEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterEngineApplication.class, args);
	}

}
