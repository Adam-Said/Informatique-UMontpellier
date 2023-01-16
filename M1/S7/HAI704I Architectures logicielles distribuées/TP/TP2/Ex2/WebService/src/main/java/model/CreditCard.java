package model;

import java.time.LocalDate;

public class CreditCard {
	private Client client;
	private String number;
	private String cvv;
	private LocalDate expiration;
	private double amount;
	
	
	
	public CreditCard() {
		this.client = new Client();
		this.number = "0000 0000 0000 0000";
		this.expiration = LocalDate.parse("2035-01-01");
		this.amount = 0;
	}
	
	public CreditCard(Client client, String number, String cvv, LocalDate expiration) {
		this.client = client;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.amount = 0;
	}
	

	public CreditCard(Client client, String number, String cvv, LocalDate expiration, double amount) {
		this.client = client;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.amount = amount;
	}
	
	public CreditCard(String number, String cvv, LocalDate expiration, double amount) {
		this.client = null;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.amount = amount;
	}
	
	public Client getName() {
		return client;
	}
	public void setName(Client client) {
		this.client = client;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public LocalDate getExpiration() {
		return expiration;
	}
	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void addMoney(double amount) {
		this.amount += amount;
	}
	
	public void subMoney(double amount) {
		this.amount -= amount;
	}

	@Override
	public String toString() {
		return "Credit card -\nClient - " + this.client.infoToString() + 
				this.number + " " + this.cvv + " " + this.expiration + "\n" +
				"Balance : " + this.amount;
	}
	
	public String cardToString() {
		return this.number + " " + this.cvv + " " + this.expiration + "\n" +
				"Balance : " + this.amount + "€";
	}
	
	
	
}
