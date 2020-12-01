package com.example.entityapi;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.zalando.problem.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

@SpringBootApplication
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
public class EntityApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(EntityApiApplication.class, args);
	}

	/**
	 * Creates object mapper with zalando problem modules.
	 * @return
	 */
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().registerModules(new ProblemModule(), new ConstraintViolationProblemModule());
	}
}
