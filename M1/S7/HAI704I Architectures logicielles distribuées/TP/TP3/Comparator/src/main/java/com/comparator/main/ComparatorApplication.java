package com.comparator.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {
		"com.comparator.models",
		"com.comparator.client",
		"com.comparator.cli",
		"com.comparator.gui",
})
public class ComparatorApplication {

	public static void main(String[] args) {
//		SpringApplication.run(ComparatorApplication.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ComparatorApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

}
