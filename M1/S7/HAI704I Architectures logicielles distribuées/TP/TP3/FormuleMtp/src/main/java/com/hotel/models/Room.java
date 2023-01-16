package com.hotel.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
	@Id
	@GeneratedValue
	@Column(name ="id")
	private long id;
	private int roomNumber;
	private double price;
	private int size;
	@ManyToOne
	@JoinColumn(name="hotel_id")
	private Hotel hotel;
	@OneToMany(mappedBy="room")
	private List<Reservation> resa;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
	
	public Room(int roomNumber, double price, int size, Hotel hotel) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.size = size;
		this.hotel = hotel;
	}
	
	public Room() {
		this.roomNumber = 0;
		this.price = 0;
		this.size = 0;
	}
	
	@Override
	public String toString() {
		return "Room n°" + roomNumber + " [" + size + "] " + price + "€\n";
	}
	
	
}
