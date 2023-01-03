package com.devs4j.cloudstream;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Devs4jCloudstreamExampleApplication {

	@Bean
	public Function<String, String> toUpperCase() {
		return String::toUpperCase;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Devs4jCloudstreamExampleApplication.class, args);
	}

}
