package server;

import javax.xml.ws.Endpoint;

import repository.HotelRepository;
import repository.HotelRepositoryImpl;
import service.HotelServiceImpl;

public class HotelPublisher {

	public static void main(String[] args) {
		HotelRepository ritz = new HotelRepositoryImpl(7);
		HotelRepository kyriad = new HotelRepositoryImpl(4);
		HotelRepository f1mtpsud = new HotelRepositoryImpl(6);
		HotelRepository f1tlsram = new HotelRepositoryImpl(2);
		HotelRepository f1tlsun = new HotelRepositoryImpl(3);
		HotelRepository crowne = new HotelRepositoryImpl(5);
		Endpoint.publish("http://localhost:8080/ritz", new HotelServiceImpl(ritz));
		Endpoint.publish("http://localhost:8080/kyriad", new HotelServiceImpl(kyriad));
		Endpoint.publish("http://localhost:8080/f1mtpsud", new HotelServiceImpl(f1mtpsud));
		Endpoint.publish("http://localhost:8080/f1tlsram", new HotelServiceImpl(f1tlsram));
		Endpoint.publish("http://localhost:8080/f1tlsun", new HotelServiceImpl(f1tlsun));
		Endpoint.publish("http://localhost:8080/crowne", new HotelServiceImpl(crowne));
		System.err.println("Server ready!"); 

	}

}
