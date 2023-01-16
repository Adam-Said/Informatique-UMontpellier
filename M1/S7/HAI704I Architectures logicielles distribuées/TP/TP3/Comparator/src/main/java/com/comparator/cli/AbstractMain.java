package com.comparator.cli;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractMain {
	public static String SERVICE_URL1;
	public static String SERVICE_URL2;
	public static String SERVICE_URL3;
	public static final String QUIT = "0";
	
	protected void setTestServiceUrl(BufferedReader inputReader) throws IOException {
		SERVICE_URL1 = "http://localhost:30007/hotelorg/api/";
		SERVICE_URL2 = "http://localhost:30008/hotelscanner/api/";
		SERVICE_URL3 = "http://localhost:30009/tripfinder/api/";
		

	}
	
	protected abstract void menu();
}
