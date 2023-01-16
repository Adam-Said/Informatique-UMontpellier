package com.agency.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.agency.models.Agency;
import com.agency.models.Hotel;
import com.agency.models.Offers;
import com.agency.repositories.AgencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AgencyController {
	@Autowired
	private RestTemplate proxy;
	@Autowired
	private AgencyRepository repository;
	private static final String uri = "hotelscanner/api";
	
	@GetMapping(uri + "/agency/count")
	public int getCountHotels() {
		int x = 0;
		Agency agence = repository.findAll().get(0);
		ArrayList<String> URIS = new ArrayList<>();
		for (Offers offer: agence.getOffers()) {
			URIS.add(offer.getUri());
		}
		for (String uri : URIS) {
			for (Offers offer: agence.getOffers()) {
				URIS.add(offer.getUri());
			}
			try {
				String uriCount = uri + "/count";
				ObjectMapper mapper = new ObjectMapper();
				String countStr = proxy.getForObject(uriCount, String.class);
				long count = (int)mapper.readValue(countStr, Map.class).get("count");
				x += count;						
			}
			catch (Exception e) {
				continue;
			}
		}
		return x;
	}
	
	
	@PutMapping(uri + "/agency/resa/{id}")
	public Hotel updateHotel(@RequestBody Hotel newHotel, @PathVariable long id) {
		Agency agence = repository.findAll().get(0);
		HashMap<Long, String> URIS = new HashMap<Long, String>();
		for (Offers offer: agence.getOffers()) {
			URIS.put(offer.getNumHotel(), offer.getUri());
		}
		String uri = URIS.get(id);
		proxy.put(uri + "/" + String.valueOf(id), newHotel);
		return newHotel;
	}
	
	@GetMapping(uri + "/agency")
	public List<Agency> getAllAgencies() {
		return repository.findAll();
	}
	
	@GetMapping(uri + "/agency/hotels")
	public List<Hotel> getAllHotels() {
		Agency agence = repository.findAll().get(0);
		ArrayList<String> URIS = new ArrayList<>();
		for (Offers offer: agence.getOffers()) {
			URIS.add(offer.getUri());
		}
		List<Hotel> hotels = new ArrayList<>();
		for (String uri : URIS) {
			try {
				Hotel[] hotel = proxy.getForObject(uri, Hotel[].class);
				for (Hotel hotel2 : hotel) {
					hotels.add(hotel2);
				}
			}
			catch (Exception e) {
				continue;
			}
			
		}
		return hotels;
	}
	
	@RequestMapping(
			  value = uri + "/agency/search",
			  params = { "position", "size", "rating", "datein", "dateout", "price" }, 
			  method = RequestMethod.GET)
	@ResponseBody
	public List<Hotel> searchHotel(@RequestParam("position") String position, @RequestParam("size") int size, @RequestParam("rating") double rating, 
			@RequestParam("datein") String datein, @RequestParam("dateout") String dateout, @RequestParam("price") double price) {
		Agency agence = repository.findAll().get(0);
		List<Hotel> toReturnHotels = new ArrayList<>();
		HashMap<String, Double> URIS = new HashMap<>();
		for (Offers offer: agence.getOffers()) {
			URIS.put(offer.getUri(), offer.getAmount());
		}
		Map<String, String> params = new HashMap<>();
		params.put("position", position);
		params.put("datein", datein);
		params.put("dateout", dateout);
		params.put("size", String.valueOf(size));
		params.put("rating", String.valueOf(rating));
		params.put("price", String.valueOf(price));
		for (String uri : URIS.keySet()) {
			try {
				String url = uri + "/search?position={position}&size={size}&rating={rating}&datein={datein}&dateout={dateout}&price={price}";
				Hotel returnedHotel = proxy.getForObject(url, Hotel.class, params);
				if(!returnedHotel.getName().equals("Undefined")) {
					returnedHotel.setImageFolder(returnedHotel.getImageFolder()  + URIS.get(uri).toString() +"d");
					toReturnHotels.add(returnedHotel);						
				}
			}
			catch (Exception e) {
				continue;
			}
		}
	return toReturnHotels;
	}
}
