package model;

public class Room {
	private int roomNumber;
	private boolean available;
	private double price;
	private int size;
	
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Room(int roomNumber, boolean available, double price, int size) {
		this.roomNumber = roomNumber;
		this.available = available;
		this.price = price;
		this.size = size;
	}
	public Room() {
		this.roomNumber = 0;
		this.available = true;
		this.price = 0;
		this.size = 1;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		if(this.available) {
			return "Room n°" + roomNumber + " [" + size + "] " + price + "€ " + "available.\n";			
		}
		else {
			return "Room n°" + roomNumber + " [" + size + "] " + price + "€ " + "not available !\n";				
		}
	}


}
