package com.hotel.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hotel.models.Hotel;
import com.hotel.models.Position;
import com.hotel.models.Room;
import com.hotel.repositories.HotelRepository;

@Configuration
public class HotelData {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public CommandLineRunner initDatabase(HotelRepository repository) {
		return args -> {
			Hotel h1 = new Hotel("Formule 1", 2.7, null, null, null, "http://hotelfinder.sc1samo7154.universe.wf/gui/formule1/");
			List<Room> rooms = new ArrayList<>();
			Room r1 = new Room(1, 45, 2, h1);
			Room r2 = new Room(2, 43, 2, h1);
			Room r3 = new Room(3, 48, 2, h1);
			Room r4 = new Room(4, 50, 2, h1);
			rooms.add(r1);
			rooms.add(r2);
			rooms.add(r3);
			rooms.add(r4);
			
			Position p1 = new Position("Montpellier", "France", "Chemin de Saint-Hubert", 1);
			h1.setAddress(p1);
			h1.setRooms(rooms);
			logger.info("Loading database with " + repository.save(h1));
		};
	}
}
