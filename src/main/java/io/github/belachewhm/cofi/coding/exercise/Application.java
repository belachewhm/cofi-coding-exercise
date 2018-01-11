package io.github.belachewhm.cofi.coding.exercise;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// @EnableCircuitBreaker
@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		log.info("Spring Boot Application Starting...");
	}
}
