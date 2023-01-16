package com.comparator.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Agency {

	private long id;
	private String agencyName;
	private List<Offers> offers;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public List<Offers> getOffers() {
		return offers;
	}
	public void setOffers(List<Offers> offers) {
		this.offers = offers;
	}

	
	public Agency(String agencyName, List<Offers> offers) {
		this.agencyName = agencyName;
		this.offers = offers;
	}
	public Agency() {
		this.agencyName = "Hotel name";
		this.offers = new ArrayList<Offers>(); 
	}
	
	public void quitAgency() {
		System.out.println("Thanks for using TripFinder\n Bye bye...");
		System.exit(0);
	}
	@Override
	public String toString() {
		String offers = "";
		for (Offers offer : this.offers) {
			offers += "\n" + offer.display();
		}
		return agencyName + ":\n Offers: " + offers;
	}
	

}
