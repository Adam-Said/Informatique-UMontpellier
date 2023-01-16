package model;

public class Position {
	private String city;
	private String country;
	private String street;
	private int number;
	private String locality;
	private String gps;
	
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public Position(String city, String country, String street, int number, String locality, String gps) {
		this.city = city;
		this.country = country;
		this.street = street;
		this.number = number;
		this.locality = locality;
		this.gps = gps;
	}
	
	public Position(String city, String country, String street, int number) {
		this.city = city;
		this.country = country;
		this.street = street;
		this.number = number;
	}
	
	public Position(String city, String country, String street, int number, String locality) {
		this.city = city;
		this.country = country;
		this.street = street;
		this.number = number;
		this.locality = locality;
	}
	public Position() {
		this.city = "None";
		this.country = "None";
		this.street = "None";
		this.number = 0;
		this.locality = "None";
		this.gps = "None";
	}
	
	public Position(String gps) {
		this.gps = gps;
	}
	
	@Override
	public String toString() {
		String str = "Position : " + number + ", " + street + ", " + city + ", " + country + "\n";			
		if(gps == null || !gps.equals("None")) {
			str += "GPS : "+ gps + "\n";
		}
		return str;
	}
	
	
	
	
	
	
	
	
}
