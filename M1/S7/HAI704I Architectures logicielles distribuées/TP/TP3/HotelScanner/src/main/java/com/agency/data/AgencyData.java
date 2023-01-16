package com.agency.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.agency.models.Agency;
import com.agency.models.Offers;
import com.agency.repositories.AgencyRepository;

@Configuration
public class AgencyData {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public CommandLineRunner initDatabase(AgencyRepository repository) {
		return args -> {
			
			Agency a1 = new Agency("Hotel Scanner", null);
			List<Offers> offers = new ArrayList<Offers>();
			Offers o1 = new Offers(1, "http://localhost:30003/formule/api/hotels", 8, a1);
			Offers o2 = new Offers(2, "http://localhost:30004/kyriad/api/hotels", 12, a1);
			Offers o3 = new Offers(3, "http://localhost:30005/formulemtp/api/hotels", 11, a1);
			
			offers.add(o1);
			offers.add(o2);
			offers.add(o3);
			
			a1.setOffers(offers);
			
			logger.info("Loading database with " + repository.save(a1));
		};
	}
}
