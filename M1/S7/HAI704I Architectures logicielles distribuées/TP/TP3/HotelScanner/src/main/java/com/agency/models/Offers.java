package com.agency.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Offers {
	@Id
	@GeneratedValue
	private long id;
	private long numHotel;
	private String uri;
	private double amount;
	@ManyToOne
	@JoinColumn(name="agency_id")
	private Agency agency;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public long getNumHotel() {
		return numHotel;
	}
	public void setNumHotel(long numHotel) {
		this.numHotel = numHotel;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Offers(long numHotel, String uri, double amount, Agency agency) {
		this.numHotel = numHotel;
		this.uri = uri;
		this.amount = amount;
		this.agency = agency;
	}
	public Offers() {
		this.numHotel = 0;
		this.uri = "";
		this.amount = 0;
	}
	
	
	@Override
	public String toString() {
		return "Offers [id=" + id + ", numHotel=" + numHotel + ", uri=" + uri + ", amount=" + amount + "]";
	}

		
}
