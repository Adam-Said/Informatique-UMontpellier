package model;

import java.time.LocalDate;
import java.util.ArrayList;


public class Hotel {
	
	// Attributes
	private String name;
	private double stars;
	private ArrayList<Room> rooms;
	private Position address;
	private ArrayList<Reservation> resa;
	private String imageFolder;
	
	// Methods
	public ArrayList<Reservation> getResa() {
		return resa;
	}
	 
	public void setResa(ArrayList<Reservation> resa) {
		this.resa = resa;
	}
	 
	public String getName() {
		return name;
	}
	 
	public void setName(String name) {
		this.name = name;
	}
	 
	public double getStars() {
		return stars;
	}
	 
	public void setStars(double stars) {
		this.stars = stars;
	}
	 
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	 
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}
	 
	public Position getAddress() {
		return address;
	}
	 
	public void setAddress(Position address) {
		this.address = address;
	}
	
	public String getImageFolder() {
		return imageFolder;
	}
	 
	public void setImageFolder(String imageFolder) {
		this.imageFolder = imageFolder;
	}
	
	// Constructors
	public Hotel(String name, double stars, ArrayList<Room> rooms, Position address, String imageFolder) {
		this.name = name;
		this.stars = stars;
		this.rooms = rooms;
		this.address = address;
		this.resa = new ArrayList<Reservation>();
		this.imageFolder = imageFolder;
	}
	
	public Hotel() {
		this.name = "Undefined";
		this.stars = 0;
		this.rooms = new ArrayList<>();
		this.address = new Position();
		this.resa = new ArrayList<Reservation>();
		this.imageFolder = "Undefined";
	}
	

    public Reservation searchResa(Client client, LocalDate inDate) {
        if (resa == null) {
            System.out.println("Aucune réservation trouvée dans cet Hotel\n");
        }
        for(Reservation res : resa) {
        	if(res.getClient().equals(client) && res.getIn().equals(inDate)) {
        		return res;
        	}
        }
		return null;
    }
}
