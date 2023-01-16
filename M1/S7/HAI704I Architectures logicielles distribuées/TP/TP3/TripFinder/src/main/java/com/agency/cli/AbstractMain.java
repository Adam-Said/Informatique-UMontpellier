package com.agency.cli;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractMain {
	public static String SERVICE_URL1;
	public static String SERVICE_URL2;
	public static String SERVICE_URL3;
	public static String SERVICE_URL4;
	public static final String QUIT = "0";
	
	protected void setTestServiceUrl(BufferedReader inputReader) throws IOException {
//		System.out.println("Provide URL to the WebService");
		SERVICE_URL1 = "http://localhost:30001/ritz/api/hotels";
		SERVICE_URL2 = "http://localhost:30002/ibis/api/hotels";
		SERVICE_URL3 = "http://localhost:30003/formule/api/hotels";
		SERVICE_URL4 = "http://localhost:30004/kyriad/api/hotels";
		
//		while(!validServiceUrl()) {
//			System.err.println("Error: "+ SERVICE_URL + " is not a valid REST URL. Try again: ");
//			SERVICE_URL = inputReader.readLine();
//		}
	}
	
	protected abstract boolean validServiceUrl();
	
	protected abstract void menu();
}
