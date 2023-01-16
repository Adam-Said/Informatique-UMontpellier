package client;

import java.util.HashMap;
import java.util.List;

import webservice.Hotel;
import webservice.HotelService;
import webservice.Room;
import webservice.Client;



public class Agency {
	private String agencyName;
	private HashMap<Client, String[]> clientInfos;
	private HashMap<HotelService, Double> offers;
	
	public Agency() {
		this.agencyName = "Hotel name";
		this.clientInfos = new HashMap<Client, String[]>();
		Client admin = new Client("admin", "admin", "0", 0);
		String[] login = {"root", "root"};
		clientInfos.put(admin, login);
		this.offers = new HashMap<HotelService, Double>();
	}

	public Agency(String name, HashMap<Client, String[]> clientInfos, HashMap<HotelService, Double> offers) {
		this.agencyName = name;
		this.clientInfos = clientInfos;
		this.offers = offers;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public HashMap<Client, String[]> getClientInfos() {
		return clientInfos;
	}

	public void setClientInfos(HashMap<Client, String[]> clientInfos) {
		this.clientInfos = clientInfos;
	}

	public HashMap<HotelService, Double> getOffers() {
		return offers;
	}

	public void setOffers(HashMap<HotelService, Double> offers) {
		this.offers = offers;
	}

	public void quitAgency() {
		System.out.println("Thanks for using TripFinder\n Bye bye...");
		System.exit(0);
	}
	
	public Hotel searchRoom(HotelService proxy, String in, String out, int size, float priceMin, float priceMax) {
		Hotel hotel = proxy.getHotel();
		hotel.getRooms().clear(); 
		List<Room> rooms = proxy.searchRoom(priceMin, priceMax, size, in, out);
		hotel.getRooms().addAll(rooms);

		return hotel;
	}
	
	public Client connectClient(String username, String pwd) {
		Client clt = null;
		for (Client i : this.clientInfos.keySet()) {
			  if(username.equals(clientInfos.get(i)[0]) && this.clientInfos.get(i)[1].equals(pwd)) {
				  clt = i;
				  break;
			  }
		}
		return clt;
	}
	
	/*public String makeReservation(HotelService proxy, Room room, String in, String out, Client client) {
		LocalDate inD = LocalDate.parse(in);
		LocalDate outD = LocalDate.parse(out);
		Reservation resa = new Reservation(client, inD, outD, room);
		proxy.addReservation(resa);
		return "Reservation bien effectuée";
	}*/
	

}
