package com.hotel.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Hotel {
	// Attributes
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	private String name;
	private double stars;
	@OneToMany(mappedBy="hotel", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Room> rooms;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="address_id", referencedColumnName = "id")
	private Position address;
	@OneToMany(mappedBy="hotelResa", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Reservation> resa;
	private String imageFolder;
	
	public List<Reservation> getResa() {
		return resa;
	}
	public void setResa(List<Reservation> resa) {
		this.resa = resa;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public List<Room> getRooms() {
		return rooms;
	}
	public void setRooms(List<Room> rooms) {
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
	
	public Hotel(String name, double stars, List<Room> rooms, Position address, List<Reservation> resa,
			String imageFolder) {
		this.name = name;
		this.stars = stars;
		this.rooms = rooms;
		this.address = address;
		this.resa = resa;
		this.imageFolder = imageFolder;
	}
	
	public Hotel(String name, double stars, List<Room> rooms, Position address) {
		this.name = name;
		this.stars = stars;
		this.rooms = rooms;
		this.address = address;
	}
	
	
	public Hotel() {
		this.name = "Undefined";
		this.stars = 0;
		this.rooms = new ArrayList<Room>();
		this.address = new Position();
		this.resa = new ArrayList<Reservation>();
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", stars=" + stars + ", rooms=" + rooms + ", address=" + address
				+ ", resa=" + resa + ", imageFolder=" + imageFolder + "]";
	}
	
	
	
	
}
