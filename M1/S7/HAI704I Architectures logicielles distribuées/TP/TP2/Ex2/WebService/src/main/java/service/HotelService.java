package service;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;

import model.Hotel;
import model.Reservation;
import model.Room;

@WebService
public interface HotelService {
	
	@WebMethod
	String roomsToString();
	
	@WebMethod
	String toString();
	
	@WebMethod
	ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, String in, String out);
	
	@WebMethod
	void addReservation(Reservation resa);

	@WebMethod
	Hotel getHotel();
}
