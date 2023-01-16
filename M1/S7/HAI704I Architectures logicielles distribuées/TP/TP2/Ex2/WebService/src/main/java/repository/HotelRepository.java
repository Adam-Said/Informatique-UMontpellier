package repository;

import java.util.ArrayList;

import model.Hotel;
import model.Reservation;
import model.Room;


public interface HotelRepository {
	
	String roomsToString();
	String toString();
	ArrayList<Room> searchRoom(float priceMin, float priceMax, int size, String in, String out);
	void addReservation(Reservation resa);
	Hotel getHotel();
}
