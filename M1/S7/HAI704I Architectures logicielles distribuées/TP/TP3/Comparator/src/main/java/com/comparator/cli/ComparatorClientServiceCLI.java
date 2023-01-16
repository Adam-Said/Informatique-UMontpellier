package com.comparator.cli;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.comparator.exceptions.ReservationException;
import com.comparator.functions.MainFunctions;
import com.comparator.gui.ClientGUI;
import com.comparator.models.Agency;
import com.comparator.models.Hotel;
import com.comparator.models.Reservation;
import com.comparator.models.Room;

@Component
public class ComparatorClientServiceCLI extends AbstractMain implements CommandLineRunner {

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
			//setTestServiceUrl(inputReader);
			URI_HOTEL = "agency";
			URI_HOTEL_ID = URI_HOTEL + "/{id}";
			URIS = new HashMap<String, String>();
			setTestServiceUrl(inputReader);
			URIS.put(SERVICE_URL1 + URI_HOTEL, SERVICE_URL1 + URI_HOTEL + "/" + URI_HOTEL_ID);
			URIS.put(SERVICE_URL2 + URI_HOTEL, SERVICE_URL2 + URI_HOTEL + "/" + URI_HOTEL_ID);
			URIS.put(SERVICE_URL3 + URI_HOTEL, SERVICE_URL3 + URI_HOTEL + "/" + URI_HOTEL_ID);
			System.out.println("How do you want to run the client ?\n1. GUI\n2. CLI");
			int choice = Integer.parseInt(inputReader.readLine());
			if(choice == 1) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ClientGUI frame = new ClientGUI(proxy);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			else {
				do {
					menu();
					userInput = inputReader.readLine();
					processUserInput(inputReader, userInput, proxy);
					
				} while (!userInput.equals(QUIT));
				System.exit(0);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void menu() {
		StringBuilder builder = new StringBuilder();
		builder.append(QUIT+". Quit.");
		builder.append("\n1. See all hotels");
		builder.append("\n2. Search hotel");
		builder.append("\n3. See agencies");
		System.out.println(builder);
	}
	
	private void processUserInput(BufferedReader reader, String userInput, RestTemplate proxy) {
		Map<String, String> params = new HashMap<>();
		try {
			switch(userInput) {
			case "1":
				int x = 0;
				ArrayList<Hotel> hotels = new ArrayList<>();
				
				for (String uri : URIS.keySet()) {
					try {
						Hotel[] returnedHotels= proxy.getForObject(uri + "/hotels", Hotel[].class);
						for (Hotel hotel : returnedHotels) {
							boolean check = true;
							for (Hotel toCompare : hotels) {
								if(hotel.getName().equals(toCompare.getName())) {
									check = false;
								}
							}
							if(check) {
								hotels.add(hotel);
								x++;
							}
						}
					}
					catch (Exception e) {
						continue;
					}
				}
				System.out.println(String.format("There are %d hotels:", x));
				for (Hotel hotel : hotels) {
					System.out.println(hotel.toString());
					for (Room room : hotel.getRooms()) {
						System.out.println(room.toString());
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
				System.out.println("\nDate in (yyyy-mm-dd): ");
				String inDate = reader.readLine();
				System.out.println("\nDate out (yyyy-mm-dd): ");
				String outDate = reader.readLine();
				System.out.println("\nNumber of persons: ");
				int size = Integer.parseInt(reader.readLine());
				params.put("position", position);
				params.put("datein", inDate);
				params.put("dateout", outDate);
				params.put("size", String.valueOf(size));
				params.put("rating", String.valueOf(rating));
				params.put("price", String.valueOf(price));
				
				HashMap<Hotel, HashMap<String, Double>> hotelMap = new HashMap<>();
				for (String uri : URIS.keySet()) {
					try {
						String url = uri + "/search?position={position}&size={size}&rating={rating}&datein={datein}&dateout={dateout}&price={price}";
						Hotel[] returnedHotel = proxy.getForObject(url, Hotel[].class, params);
						for (Hotel hotel : returnedHotel) {
							if(!hotel.getName().equals("Undefined")) {
								HashMap<String, Double> agencyMap = new HashMap<>();
								int lastIndex = hotel.getImageFolder().lastIndexOf("/");
								double discount = Double.parseDouble(hotel.getImageFolder().substring(lastIndex + 1));
								agencyMap.put(uri, discount);
								hotel.setImageFolder(hotel.getImageFolder().substring(0,lastIndex));
								hotelMap.put(hotel, agencyMap);
							}
						}
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				for (int i= 0; i < hotelMap.size() ; i++) {
					for (int j= 0; j < hotelMap.size() ; j++) {
						if(i != j) {
							try {
								Hotel hotel = (Hotel) hotelMap.keySet().toArray()[i];
								Hotel toCompare = (Hotel) hotelMap.keySet().toArray()[j];
								HashMap<String, Double> agency1 = hotelMap.get(hotel);
								String agencyUrl1 = (String) agency1.keySet().toArray()[0];
								double discount1 = agency1.get(agencyUrl1);
								if(hotel.getName().equals(toCompare.getName())) {
									HashMap<String, Double> agency2 = hotelMap.get(toCompare);
									String agencyUrl2 = (String) agency2.keySet().toArray()[0];
									double discount2 = agency2.get(agencyUrl2);
									if(discount1 >= discount2) {
										hotelMap.remove(toCompare);									
									}
								}
							}
							catch (Exception e) {
								continue;
							}
						}
					}
					
				}

				System.out.println("Results:\n");
				for (Hotel hotel : hotelMap.keySet()) {
					if(!hotel.getName().equals("Undefined")) {
						System.out.println(hotel.toString());
						String uri = (String) hotelMap.get(hotel).keySet().toArray()[0];
						proxy.getForObject(uri, Agency[].class);
						Agency[] agencies = proxy.getForObject(uri, Agency[].class);
						System.out.println("The best price is provided to you by "+ agencies[0].getAgencyName() + " with " 
						+ hotelMap.get(hotel).get(uri).toString() +"% discount !");
						for (Room room: hotel.getRooms()) {
							System.out.println(room.toString());
						}
						System.out.println();
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
					else if(hotelChoice > hotelMap.size() || hotelChoice <= -1) {
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
						Hotel selectedHotel =(Hotel) hotelMap.keySet().toArray()[hotelChoice - 1];
						Room selectedRoom = selectedHotel.getRooms().get(roomChoice-1);
						Reservation resa = MainFunctions.makeReservation(reader, ind, outd, selectedRoom, selectedHotel, selectedRoom.getPrice());
						selectedHotel.setResa(new ArrayList<>());
						selectedHotel.getResa().add(resa);
						// probleme ici
						String agencyURI = "";
						double discount = 0;
						for(Entry<Hotel, HashMap<String, Double>> map: hotelMap.entrySet()) {
							if(map.getKey().equals(selectedHotel)) {
								agencyURI = (String) map.getValue().keySet().toArray()[0];
								discount = map.getValue().get(agencyURI);
								break;
							}
						}
						double newPrice = selectedRoom.getPrice() * discount;
						resa.setAmount(newPrice);
						String url = agencyURI + "/resa/" + String.valueOf(selectedHotel.getId());
						proxy.put(url, selectedHotel);
						System.out.println("Your order have been placed. Thank you for your purchase !\n");
						MainFunctions.getRecipe(selectedHotel, resa.getClient(), resa);
						MainFunctions.makePdf(selectedHotel, resa.getClient(), resa);
						
					} catch (ReservationException e) {
						e.printStackTrace();
						break;
					}					
				}
						
				break;
			case "3":
				System.out.println("Agencies:");
				
				for (String uri : URIS.keySet()) {
					try {
						proxy.getForObject(uri, Agency[].class);
						Agency[] agencies = proxy.getForObject(uri, Agency[].class); 
						Arrays.asList(agencies).forEach(System.out::println);
					}
					catch(Exception e) {
						continue;
					}
					System.out.println();
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
