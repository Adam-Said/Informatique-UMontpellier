package com.hotel.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.exceptions.HotelNotFoundException;
import com.hotel.models.Hotel;
import com.hotel.models.Position;
import com.hotel.models.Reservation;
import com.hotel.models.Room;
import com.hotel.repositories.HotelRepository;

@RestController
public class HotelController {
	
	@Autowired
	private HotelRepository repository;
	private static final String uri = "ritz/api";
	
	@GetMapping(uri + "/hotels")
	public List<Hotel> getAllHotels() {
		return repository.findAll();
	}
	
	@GetMapping(uri + "/hotels/count")
	public String count() {
		return String.format("{\"%s\": %d}", "count", repository.count());
	}
	
	@GetMapping(uri + "/hotels/{id}")
	public Hotel getHotelById(@PathVariable long id) throws HotelNotFoundException {
		return repository.findById(id).orElseThrow(() -> new HotelNotFoundException("Error : could not found hotel by ID"));
	}
	
	@RequestMapping(
			  value = uri + "/hotels/search", 
			  params = { "position", "size", "rating", "datein", "dateout", "price" }, 
			  method = RequestMethod.GET)
	@ResponseBody
	public Hotel searchHotel(@RequestParam("position") String position, @RequestParam("size") int size, @RequestParam("rating") double rating, 
			@RequestParam("datein") String datein, @RequestParam("dateout") String dateout, @RequestParam("price") double price) throws HotelNotFoundException {
		List<Hotel> hotels = repository.findAll();
		Position p = hotels.get(0).getAddress();
		double stars = hotels.get(0).getStars();
		LocalDate in = LocalDate.parse(datein);
		LocalDate out = LocalDate.parse(dateout);
		Hotel toReturnHotel = hotels.get(0);
		List<Room> newRooms = new ArrayList<Room>();
		if((p.getCity().contains(position) || p.getCountry().contains(position)) && stars >= rating) {
			for (Room room : hotels.get(0).getRooms()) {
				double roomPrice = room.getPrice();
				int roomSize = room.getSize();
				if(roomPrice <= price && roomSize >= size) {
					boolean isOkay = true;
					for (Reservation resa : hotels.get(0).getResa()) {
						if(resa.getRoom().getRoomNumber() == room.getRoomNumber()) {
							if((in.isAfter(resa.getIn()) && in.isBefore(resa.getOut()))
								|| (out.isAfter(resa.getIn()) && out.isBefore(resa.getOut()))
								|| ((in.isBefore(resa.getIn()) && out.isAfter(resa.getOut())))
								|| (in.isAfter(resa.getIn()) && out.isBefore(resa.getOut()))
								|| (in.isEqual(resa.getIn()) || out.isEqual(resa.getOut()))) {
								isOkay = false;
								continue;
							}
						}
					}
					if(isOkay) {
						newRooms.add(room);
					}
				}
			}
			if(newRooms.size() != 0) {
				toReturnHotel.setRooms(newRooms);
				return toReturnHotel;
			}
		}
		return new Hotel();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(uri + "/hotels")
	public Hotel createHotel(@RequestBody Hotel hotel) {
		return repository.save(hotel);
	}
	
	@PutMapping(uri + "/hotels/{id}")
	public Hotel updateHotel(@RequestBody Hotel newHotel, @PathVariable long id) {
		return repository.findById(id).map(hotel -> {
			Reservation resa = new Reservation();
			if(hotel.getResa() == null) {
				hotel.setResa(new ArrayList<Reservation>());
			}
			resa = newHotel.getResa().get(0);
			resa.setHotelResa(newHotel);
			hotel.getResa().add(resa);				
			repository.save(hotel);
			return hotel;
		}).orElseGet(() -> {
			newHotel.setId(id);
			repository.save(newHotel);
			return newHotel;
		});
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(uri + "/hotels/{id}")
	public void deleteEmployee(@PathVariable long id) throws HotelNotFoundException{
		Hotel hotel = repository.findById(id).orElseThrow(() -> new HotelNotFoundException("Error : could not found hotel by ID"));
		repository.delete(hotel);
		
	}
}
