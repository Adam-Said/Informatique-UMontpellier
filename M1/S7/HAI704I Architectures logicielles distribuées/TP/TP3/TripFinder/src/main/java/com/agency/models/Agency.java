package com.agency.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agency {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	private String agencyName;
	@OneToMany(mappedBy="agency", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
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
		return "Agency [agencyName=" + agencyName + ", offers=" + offers + "]";
	}
	

}
