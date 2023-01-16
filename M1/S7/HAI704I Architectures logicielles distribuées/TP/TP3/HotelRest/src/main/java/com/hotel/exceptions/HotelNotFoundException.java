package com.hotel.exceptions;

public class HotelNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public HotelNotFoundException() {
		super();
	}
	
	public HotelNotFoundException(String msg) {
		super(msg);
	}
}
