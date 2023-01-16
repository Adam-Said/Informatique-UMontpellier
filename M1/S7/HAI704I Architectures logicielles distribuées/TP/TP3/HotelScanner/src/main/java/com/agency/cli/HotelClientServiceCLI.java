package com.agency.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.agency.exceptions.ReservationException;
import com.agency.functions.MainFunctions;
import com.agency.models.Hotel;
import com.agency.models.Reservation;
import com.agency.models.Room;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HotelClientServiceCLI extends AbstractMain implements CommandLineRunner {

	@Autowired
	private RestTemplate proxy;
	private static Map<String, String> URIS;
	private static String URI_HOTEL;
	private static String URI_HOTEL_ID;
	
	@Override
	public void run(String... args) throws Exception {
		BufferedReader inputReader;
		String userInput = "";
		try {
			inputReader = new BufferedReader(new InputStreamReader(System.in));
			setTestServiceUrl(inputReader);
			URI_HOTEL = "hotels";
			URI_HOTEL_ID = URI_HOTEL + "/{id}";
			URIS = new HashMap<String, String>();
			URIS.put(SERVICE_URL1 + "hotels", SERVICE_URL1 + URI_HOTEL_ID);
			URIS.put(SERVICE_URL2 + "hotels", SERVICE_URL2 + URI_HOTEL_ID);
			URIS.put(SERVICE_URL3 + "hotels", SERVICE_URL3 + URI_HOTEL_ID);
			do {
				menu();
				userInput = inputReader.readLine();
				processUserInput(inputReader, userInput, proxy);
				
			} while (!userInput.equals(QUIT));
			System.exit(0);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected boolean validServiceUrl() {
		return SERVICE_URL1.equals("http://localhost:8080/hotelservice/api");
	}

	@Override
	protected void menu() {
		StringBuilder builder = new StringBuilder();
		builder.append(QUIT+". Quit.");
		builder.append("\n1. See all hotels");
		builder.append("\n2. Search hotel");
		builder.append("\n3. Make reservation");
		System.out.println(builder);	
	}
	
	private void processUserInput(BufferedReader reader, String userInput, RestTemplate proxy) {
		Map<String, String> params = new HashMap<>();
		try {
			switch(userInput) {
			case "1":
				int x = 0;
				for (String uri : URIS.keySet()) {
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
				System.out.println(String.format("There are %d hotels:", x));
				for (String uri : URIS.keySet()) {
					try {
						Hotel[] hotels = proxy.getForObject(uri, Hotel[].class);
						Arrays.asList(hotels).forEach(System.out::println);						
					}
					
					catch (Exception e) {
						continue;
					}
				}
				break;

			case "2":
				System.out.println("Where do you want to go ? (City or country)\n");
				String position = reader.readLine();
				System.out.println("\nRating: ");
				double rating = Double.parseDouble(reader.readLine());
				System.out.println("\nPrice: ");
				double price = Double.parseDouble(reader.readLine());
				System.out.println("\nDate in: ");
				String inDate = "2022-05-05";
				System.out.println("\nDate out: ");
				String outDate = "2022-05-06";
				System.out.println("\nNumber of persons: ");
				int size = Integer.parseInt(reader.readLine());
				params.put("position", position);
				params.put("datein", inDate);
				params.put("dateout", outDate);
				params.put("size", String.valueOf(size));
				params.put("rating", String.valueOf(rating));
				params.put("price", String.valueOf(price));
				
				List<Hotel> resultHotel = new ArrayList<>(); 
				int cpt = 1;
				ArrayList<String> uriList = new ArrayList<>();
				System.out.println("Results:\n");
				for (String uri : URIS.keySet()) {
					try {
						String url = uri + "/search?position={position}&size={size}&rating={rating}&datein={datein}&dateout={dateout}&price={price}";
						Hotel returnedHotel = proxy.getForObject(url, Hotel.class, params);
						if(!returnedHotel.getName().equals("Undefined")) {
							uriList.add(uri);
							resultHotel.add(returnedHotel);
							System.out.println("Hotel n°"+ String.valueOf(cpt));
							cpt++;
							System.out.println(returnedHotel.toString());
							for (Room room: returnedHotel.getRooms()) {
								System.out.println(room.toString());
							}
							System.out.println();						
						}
					}
					catch (Exception e) {
						continue;
					}
				}
				
				
				System.out.println("Would you like to order one of these ?\n");
				int hotelChoice = -1;
				int roomChoice = 0;
				while(hotelChoice == -1) {
					System.out.println("Hotel number (0 to exit): ");
					hotelChoice = Integer.parseInt(reader.readLine());
					if(hotelChoice == 0) {
						System.out.println("Quitting hotel research...");
						break;
					}
					else if(hotelChoice > resultHotel.size() || hotelChoice <= -1) {
						System.err.println("Impossible choice !");
						hotelChoice = -1;
					}
					else {
						System.out.println("Room number : ");
						roomChoice = Integer.parseInt(reader.readLine());
					}
				}
				LocalDate ind = LocalDate.parse(inDate);
				LocalDate outd = LocalDate.parse(outDate);
				if(hotelChoice != 0 && roomChoice != 0) {
					try {
						Hotel selectedHotel = resultHotel.get(hotelChoice-1);
						Room selectedRoom = selectedHotel.getRooms().get(roomChoice-1);
						Reservation resa = MainFunctions.makeReservation(reader, ind, outd, selectedRoom, selectedHotel, selectedRoom.getPrice());
						selectedHotel.setResa(new ArrayList<Reservation>());
						selectedHotel.getResa().add(resa);
						params.put("id", String.valueOf(selectedHotel.getId()));
						String uriID = URIS.get(uriList.get(hotelChoice-1));
						proxy.put(uriID, selectedHotel, params);
						System.out.println("Your order have been placed. Thank you for your purchase !\n");
						MainFunctions.getRecipe(selectedHotel, resa.getClient(), resa);
						MainFunctions.makePdf(selectedHotel, resa.getClient(), resa);
						
					} catch (ReservationException e) {
						e.printStackTrace();
						break;
					}					
				}
						
				break;
				
			case QUIT:
				System.out.println("Bye bye...");
				return;
			default:
				System.err.println("Wrong input. Try again: ");
				return;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (HttpClientErrorException e) {
			System.err.println(e.getMessage());
		}
	}

}
