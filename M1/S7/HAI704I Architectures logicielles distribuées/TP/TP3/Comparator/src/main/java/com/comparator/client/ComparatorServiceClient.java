package com.comparator.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ComparatorServiceClient {

	@Bean
	public RestTemplate genereateRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
