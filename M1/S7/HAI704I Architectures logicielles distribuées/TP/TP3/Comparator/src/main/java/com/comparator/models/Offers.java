package com.comparator.models;

public class Offers {

	private long id;
	private long numHotel;
	private String uri;
	private double amount;
	
	
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
	public Offers(long numHotel, String uri, double amount) {
		this.numHotel = numHotel;
		this.uri = uri;
		this.amount = amount;
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
	
	public String display() {
		return "- " + this.getNumHotel() + ": " + this.getUri() + " | " + String.valueOf(this.getAmount()) + "% discount";
	}

		
}
