package com.hotel.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
		"com.hotel.models"
})
@EnableJpaRepositories(basePackages = {
	"com.hotel.repositories"
})
@SpringBootApplication(scanBasePackages = {
		"com.hotel.data",
		"com.hotel.exceptions",
		"com.hotel.controllers"
})
public class HotelRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelRestApplication.class, args);
	}

}
