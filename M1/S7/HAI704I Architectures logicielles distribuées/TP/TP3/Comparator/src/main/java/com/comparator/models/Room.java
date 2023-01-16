package com.comparator.models;

public class Room {

	private long id;
	private int roomNumber;
	private double price;
	private int size;
	private Hotel hotel;
	
	
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public Room(int roomNumber, double price, int size) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.size = size;
	}
	public Room() {
		this.roomNumber = 0;
		this.price = 0;
		this.size = 0;
	}
	
	@Override
	public String toString() {
		return "Room n°" + roomNumber + " [" + size + "] " + price + "€";
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
