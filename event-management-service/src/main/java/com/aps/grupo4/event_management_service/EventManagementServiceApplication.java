package com.aps.grupo4.event_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class EventManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagementServiceApplication.class, args);
	}

}
