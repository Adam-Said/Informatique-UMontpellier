package model;

import java.time.LocalDate;

public class Reservation {
	
	private Client client;
	private LocalDate in;
	private LocalDate out;
	private CreditCard cc;
	private Room room;
	
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
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
	public CreditCard getCc() {
		return cc;
	}
	public void setCc(CreditCard cc) {
		this.cc = cc;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Reservation(Client client, LocalDate in, LocalDate out, Room room) {
		this.client = client;
		this.in = in;
		this.out = out;
		this.cc = client.getCc();
		this.room = room;
	}
	@Override
	public String toString() {
		return "Reservation : " + client.infoToString() + "room n°" + room.getRoomNumber() + "\n"+
				"From " + in + " to " + out + "\n";
	}
	



}
