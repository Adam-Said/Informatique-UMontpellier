package com.comparator.models;

import java.time.LocalDate;


public class Reservation {

	private long id;
	private String client;
	private LocalDate in;
	private LocalDate out;
	private double amount;
	private Room room;
	@SuppressWarnings("unused")
	private Hotel hotel;
	
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
		this.hotel = hotel;
	}
		
	
	public Reservation() {
		this.client = "null";
		this.in = LocalDate.parse("2000-01-01");
		this.out = LocalDate.parse("2000-01-01");
		this.amount = 0;
		this.hotel = new Hotel();
		this.room = new Room();
	}
	@Override
	public String toString() {
		return "Reservation : " + client + "room n°" + room + "\n"+
				"From " + in + " to " + out + "\n";
	}
	
	

	public static String formRecipe(int size, String element) {
		String display = "";
		String firstLine = "+";
		String middleLine = "|";
		int wSpace = size - 2;

		if(element.equals("footer")) {
			display = "+";
			for(int i = 0; i < wSpace; i++) {
				display = display + "-"; 
			}
			return display + "+" + "\n";
		}

		for(int i = 0; i < wSpace; i++) {
			firstLine = firstLine + "-";
		}
		firstLine = firstLine + "+";
		String endLine = firstLine;
		firstLine = firstLine + "\n";
		wSpace = wSpace - element.length();
		int edges = 0;

		if(wSpace % 2 == 0) {
			edges = wSpace/2;
			String separators = "";
			for(int k = 0; k < edges; k++) {
				separators = separators + " ";
			}
			middleLine = middleLine + separators + element + separators + "|" + "\n";
		} else if (wSpace % 2 != 0) {
			edges = wSpace/2;
			String separators = "";
			for(int k = 0; k < edges; k++) {
				separators = separators + " ";
			}
			middleLine = middleLine + separators + " " + element + separators + "|" + "\n";
		}

		display = firstLine + middleLine + endLine;
		return display;
	}

	public static String adaptiveDisplay(String choice, String element, int size) {
		int wSpace = 0;
		String startLine = "| ";

		if(choice.equals("hotelName")) {
			String endLine = "";
			startLine = startLine + "Hotel : ";
			wSpace = (size - 11) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("room")) {
			String endLine = "";
			startLine = startLine + "Room : ";
			wSpace = (size - 10) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if (choice.equals("datein")) {
			String endLine = "";
			startLine = startLine + "Arrival Date : ";
			wSpace = (size - 18) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("dateout")) {
			String endLine = "";
			startLine = startLine + "Departure Date : ";
			wSpace = (size - 20) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("price")) {
			String endLine = "";
			startLine = startLine + "Price : ";
			wSpace = (size - 11) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("info")) {
			wSpace = (size - 3) - element.length();
			String endLine = "";
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		}
		return null;
	}
}
