package com.devs4j.cloudstream;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class Devs4jCloudstreamExampleApplication {

	
	private static final Logger log = LoggerFactory.getLogger(Devs4jCloudstreamExampleApplication.class);

	
	/*
	 * producer-out-0
	 */
	//@Bean
	public Supplier<Flux<Long>> producer() {
		return () -> Flux.interval(Duration.ofSeconds(1)).log();
	}
	
	/*
	 * processor-in-0
	 * processor-out-0
	 */
	//@Bean
	public Function<Flux<Long>, Flux<Long>> processor() {
		return flx -> flx.map(nmbr -> nmbr * nmbr);
	}
	
	/*
	 * consumer-in-0
	 */
	//@Bean
	public Consumer<Long> consumer() {
		return (number) -> {
			log.info("message received {}", number);
		};
	}
	
	@Bean
	public Function<String, String> toUpperCase() {
		return String::toUpperCase;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Devs4jCloudstreamExampleApplication.class, args);
	}

}
