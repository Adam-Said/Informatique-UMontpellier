package com.hotel.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation")
public class Reservation {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	private String client;
	@Column(name = "dateIn")
	private LocalDate in;
	@Column(name = "dateOut")
	private LocalDate out;
	private double amount;
	@ManyToOne
	@JoinColumn(name ="room_id", nullable=true)
	private Room room;
	@ManyToOne
	@JoinColumn(name="hotel_id", nullable=true)
	private Hotel hotelResa;
	
	
	public void setHotelResa(Hotel hotelResa) {
		this.hotelResa = hotelResa;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public LocalDate getIn() {
		return in;
	}
	public void setIn(LocalDate in) {
		this.in = in;
	}
	public LocalDate getOut() {
		return out;
	}
	public void setOut(LocalDate out) {
		this.out = out;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
		
	public Reservation(String client, LocalDate in, LocalDate out, double amount, Room room, Hotel hotel) {
		this.client = client;
		this.in = in;
		this.out = out;
		this.amount = amount;
		this.room = room;
		this.hotelResa = hotel;
	}
		
	
	public Reservation() {
		this.client = "null";
		this.in = LocalDate.parse("2000-01-01");
		this.out = LocalDate.parse("2000-01-01");
		this.amount = 0;
		this.room = new Room();
		this.hotelResa = new Hotel();
	}
	@Override
	public String toString() {
		return "Reservation : " + client + "room n°" + room + "\n"+
				"From " + in + " to " + out + "\n";
	}
}
