package com.agency.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {
		"com.agency.models"
})
@EnableJpaRepositories(basePackages = {
		"com.agency.repositories"
})
@SpringBootApplication(scanBasePackages = {
		"com.agency.data",
		"com.agency.controllers",
		"com.agency.exceptions",
		"com.agency.client",
//		"com.agency.cli"
})
public class AgencyRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgencyRestApplication.class, args);
	}
}
