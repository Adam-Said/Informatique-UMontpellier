package com.hotel.exceptions;

public class HotelException extends Exception{

	private static final long serialVersionUID = 1L;

	public HotelException() {
		super();
	}
	
	public HotelException(String msg) {
		super(msg);
	}
}
