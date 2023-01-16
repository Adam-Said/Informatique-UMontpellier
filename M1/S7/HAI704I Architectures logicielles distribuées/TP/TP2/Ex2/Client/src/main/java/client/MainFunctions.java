package client;

import java.awt.Desktop;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import exception.ReservationException;
import webservice.Client;
import webservice.CreditCard;
import webservice.Hotel;
import webservice.HotelService;
import webservice.HotelServiceImplService;
import webservice.Reservation;
import webservice.Room;

public class MainFunctions {
	
	public static Agency MakeAgence(int ID) {
		HashMap<HotelService, Double> offers = new HashMap<>();
		HashMap<Client, String[]> clients = new HashMap<>();
		String name = "";
		try {  
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://dakota.o2switch.net:3306/sc1samo7154_hotelfinderdb","sc1samo7154_hotelviewer","hotelfinderviewer");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from Agency where id="+ ID);
			if(rs.next()) {
				name = rs.getString("Name");
			}
			rs=stmt.executeQuery("select * from ListePartners where Agency="+ ID);
			HashMap<Integer, Float> partners = new HashMap<Integer, Float>();
			while(rs.next()) {
				partners.put(rs.getInt("Hotel"), rs.getFloat("Amount"));
			}
			for(int i : partners.keySet()) {
				switch (i) {
					case 2 :
						offers.put(new HotelServiceImplService(new URL("http://localhost:8080/f1tlsram?wsdl")).getHotelServiceImplPort(), (double)partners.get(i));
						break;
					case 3 :
						offers.put(new HotelServiceImplService(new URL("http://localhost:8080/f1tlsun?wsdl")).getHotelServiceImplPort(), (double)partners.get(i));
						break;
					case 4 :
						offers.put(new HotelServiceImplService(new URL("http://localhost:8080/kyriad?wsdl")).getHotelServiceImplPort(), (double)partners.get(i));
						break;
					case 5 :
						offers.put(new HotelServiceImplService(new URL("http://localhost:8080/crowne?wsdl")).getHotelServiceImplPort(), (double)partners.get(i));
						break;
					case 6 :
						offers.put(new HotelServiceImplService(new URL("http://localhost:8080/f1mtpsud?wsdl")).getHotelServiceImplPort(), (double)partners.get(i));
						break;
					case 7 :
						offers.put(new HotelServiceImplService(new URL("http://localhost:8080/ritz?wsdl")).getHotelServiceImplPort(), (double)partners.get(i));
						break;
				}
			}
			rs=stmt.executeQuery("select * from ListeClients where Agency="+ID);
			ArrayList<Integer> listeClients = new ArrayList<>();
			while(rs.next()) {
				listeClients.add(rs.getInt("Client"));
			}
			for(int i = 0; i < listeClients.size(); i++) {
				int id = listeClients.get(i);
				Client clt = new Client();
				int cc = 0;
				ArrayList<String> cred = new ArrayList<String>();

				rs=stmt.executeQuery("select * from Client where ID="+id);
				if(rs.next()) {
					clt.setName(rs.getString("Name"));
					clt.setFirstname(rs.getString("Firstname"));
					clt.setAge(rs.getInt("Age"));
					clt.setTelNumber(rs.getString("Tel"));
					cc = rs.getInt("CreditCard");
					cred.add(rs.getString("Login"));
					cred.add(rs.getString("Password"));
				}

				rs = stmt.executeQuery("select * from CreditCard where ID="+ cc);
				CreditCard card = new CreditCard();
				if(rs.next()) {
					card.setNumber(rs.getString("Number"));
					card.setCvv(rs.getString("CVV"));
					card.setExpiration(rs.getDate("Expiration").toLocalDate());
					card.setAmount(rs.getFloat("Amount"));
					card.setName(clt);
					
				}
				
				clt.setCc(card);
				String[] log = {cred.get(0), cred.get(1)};
				clients.put(clt, log);					
				
			}
		}
		catch (Exception e) {
			System.err.println(e);
		}
		
		return new Agency(name, clients, offers);
	}
	

	public static Client connectClient(Agency agency ) {
		try {
			Scanner textScanner = new Scanner(System.in);
			String username = "";
			String pwd = "";
			System.out.println("Please enter your username :\n");
			username = textScanner.nextLine();
			System.out.println("Please enter your password :\n");
			pwd = textScanner.nextLine();
			Client client = null;
			client = agency.connectClient(username, pwd);
			while(client == null) {
				System.out.println("Wrong credentials!\n");
				System.out.println("Please enter your username :\n");
				username = textScanner.nextLine();
				System.out.println("Please enter your password :\n");
				pwd = textScanner.nextLine();
				client = agency.connectClient(username, pwd);
			}

			return client;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Client connectClient(String login, String password, Agency agency) {
		try {
			String username = login;
			String pwd = password;
			Client client = null;
			client = agency.connectClient(username, pwd);
			return client;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void hotelFinder(Agency agency, Client client) {
		try  {
			Scanner textScanner = new Scanner(System.in);
			Scanner intScanner = new Scanner(System.in);
			System.out.println("Where do you want to go ?\n");
			String location = textScanner.nextLine();
			System.out.println("When would you like to go ? (yyyy-MM-dd))\n");
			String in = textScanner.nextLine();
			System.out.println("When would you like to leave ? (yyyy-MM-dd))\n");
			String out = textScanner.nextLine();
			System.out.println("How many people will be with you ?\n");
			int size = intScanner.nextInt();
			System.out.println("Select your range of price\n Price min : ");
			int priceMin = intScanner.nextInt();
			System.out.println("Price max : ");
			int priceMax = intScanner.nextInt();
			System.out.println("Minimum of rating: ");
			double rating = intScanner.nextInt();
			System.out.println("Looking for the best offers...\n");
			
			HashMap<Hotel, Double> hotels = research(agency, location, size, in, out, priceMin, priceMax, rating);
			
			if(hotels.isEmpty()) {
				System.err.println("Sorry, no hotels corresponding to your needs.");
				return;
			}
			
			ArrayList<Hotel> hotelList = new ArrayList<>();
			int cpt = 1;
			for (Entry<Hotel, Double> prox : hotels.entrySet()) {
				Hotel hotel = prox.getKey();
				hotelList.add(hotel);
				String starsString = String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(hotel.getStars())));
				System.out.println(hotel.getName() + " " + starsString + "\n" + hotel.getAddress().toString());
				for(int j = 1; j <= hotel.getRooms().size(); j++) {
					Room room = hotel.getRooms().get(j-1);
					System.out.println("N°" + cpt + "-" + j + " : " + room.toString());
				}	
				System.out.println();
				cpt++;
			}
			

			System.out.println("Would you like to order one of these ?\n");
			int hotelChoice = -1;
			int roomChoice = 0;
			while(hotelChoice == -1) {
				System.out.println("Hotel number (0 to exit): ");
				hotelChoice = intScanner.nextInt();
				if(hotelChoice == 0) {
					System.out.println("Quitting hotel research...");
					return;
				}
				else if(hotelChoice > hotels.size() || hotelChoice <= -1) {
					System.err.println("Impossible choice !");
					hotelChoice = -1;
				}
				else {
					System.out.println("Room number : ");
					roomChoice = intScanner.nextInt();
				}
			}
			LocalDate ind = LocalDate.parse(in) ;
			LocalDate outd = LocalDate.parse(out); 
			try {
				makeReservation(agency, client, ind, outd, hotelList.get(hotelChoice-1).getRooms().get(roomChoice-1), hotelList.get(hotelChoice-1), hotels.get(hotelList.get(hotelChoice-1)));
			} catch (ReservationException e) {
				e.printStackTrace();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void hotelFinderGUI(Agency agency, Client client, String City, int bedNumbers, int minPrice, int maxPrice, double minRating, String outDate, String inDate) {
		try  {
			String location = City;
			String in = inDate;
			String out = outDate;
			int size = bedNumbers;
			int priceMin = minPrice;
			int priceMax = maxPrice;
			double rating = minRating;
			
			HashMap<Hotel, Double> hotels = research(agency, location, size, in, out, priceMin, priceMax, rating);
			
			if(hotels.isEmpty()) {
				System.err.println("Sorry, no hotels corresponding to your needs.");
				return;
			}
			
			ArrayList<Hotel> hotelList = new ArrayList<>();
			int cpt = 1;
			for (Entry<Hotel, Double> prox : hotels.entrySet()) {
				Hotel hotel = prox.getKey();
				hotelList.add(hotel);
				System.out.println(hotel.getName() + " " + hotel.getStars() + "\n" + hotel.getAddress().toString() +"");
				for(int j = 1; j <= hotel.getRooms().size(); j++) {
					Room room = hotel.getRooms().get(j-1);
					System.out.println("N°" + cpt + "-" + j + " : " + room.toString());
				}	
				System.out.println();
				cpt++;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	public static HashMap<Hotel, Double> research(Agency agency, String location, int size, String in, String out, int priceMin, int priceMax, double rating) {
		HashMap<Hotel, Double> hotels = new HashMap<>();
		HashMap<HotelService, Double> proxy = agency.getOffers();
		try {  
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(
					"jdbc:mysql://dakota.o2switch.net:3306/sc1samo7154_hotelfinderdb","sc1samo7154_hotelviewer","hotelfinderviewer");
			Statement stmt=con.createStatement();
			for (Entry<HotelService, Double> prox : proxy.entrySet()) {
				HotelService hotel = prox.getKey();
				if((hotel.getHotel().getAddress().getCity().equals(location) || hotel.getHotel().getAddress().getCountry().equals(location))
						&& hotel.getHotel().getStars() >= rating) {
					Hotel results = agency.searchRoom(hotel, in, out, size, priceMin, priceMax);
					ResultSet rs=stmt.executeQuery("select ID from Hotel where Name='"+results.getName()+"'");
					int id = 0;
					if(rs.next()) {
						id = rs.getInt("ID");
					}
					
					rs=stmt.executeQuery("select * from Reservation where Hotel="+ id);
					ArrayList<Integer> listID = new ArrayList<>();
					while(rs.next()) {
						listID.add(rs.getInt("Room"));
					}
					
					ArrayList<Integer> roomNums = new ArrayList<Integer>();
					for(int roomID : listID) {
						ResultSet rs2=stmt.executeQuery("select * from Room where ID="+ roomID);
						if(rs2.next()) {
							roomNums.add(rs2.getInt("Number"));
						}
						rs2.close();
					}
					
					ArrayList<Room> newRooms = new ArrayList<Room>();
					for(Room room : results.getRooms()) {
						boolean check = true;
						for(int roomNum : roomNums) {
							if(room.getRoomNumber() == roomNum) {
								check = false;
							}
						}
						if(check) {
							newRooms.add(room);
						}
					}
					
					if(!newRooms.isEmpty()) {
						for (Room room : newRooms) {
							room.setPrice(room.getPrice() - (room.getPrice() / 100 ) * agency.getOffers().get(hotel));
						}
						results.setRoom(newRooms);
						hotels.put(results, prox.getValue());						
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return hotels;
	}

	public static void makeReservation(Agency agency, Client client, LocalDate in, LocalDate out, Room room, Hotel hotel, double amount) throws ReservationException {
		Reservation resa = null;
		System.out.println("Use your saved payment method ? [y/n]");
		try {
			Scanner textScanner = new Scanner(System.in);
			String choice = textScanner.nextLine();
			if(choice.equals("y")) {
				CreditCard cc = client.getCc();
				if(cc == null || cc.getCvv() == "000") {
					System.err.println("No payment method found !");
				}
				else {
					System.out.println("Do you confirm your purchase ? [y/n]");
					String confirm = textScanner.nextLine();
					if(confirm.equals("y")) {
						double creditCardBalance = client.getCc().getAmount();
						double price = room.getPrice() - (room.getPrice() / 100) * amount;
						if(creditCardBalance >= price) {
							client.subMoney(price);
							resa = new Reservation(client, in, out, room);
							hotel.getResa().add(resa);
							try{  
								Class.forName("com.mysql.jdbc.Driver");
								Connection con=DriverManager.getConnection(
								"jdbc:mysql://dakota.o2switch.net:3306/sc1samo7154_hotelfinderdb","sc1samo7154_hotelupdate","hotelupdate34");
								Statement stmt=con.createStatement();
								ResultSet rs = stmt.executeQuery("SELECT ID FROM Client WHERE "
										+ "Name='"+client.getName() +"' AND Firstname='" + client.getFirstname()+"'");
								int clientID = 0; 
								if(rs.next()) {
									clientID = rs.getInt("ID");
								}
								
								rs = stmt.executeQuery("SELECT ID FROM Hotel WHERE Name='"+hotel.getName()+"'");
								int hotelID = 0;
								if(rs.next()) {
									hotelID = rs.getInt("ID");
								}
								
								rs = stmt.executeQuery("SELECT ID FROM Room WHERE Number="+ room.getRoomNumber()+" AND Hotel="+hotelID);
								int roomID = 0; 
								if(rs.next()) {
									roomID = rs.getInt("ID");
								}

								PreparedStatement preparedStmt = con.prepareStatement(
										"INSERT INTO `Reservation` (`ID`, `Client`, `Room`, `DateIn`, `DateOut`, `Price`, `Hotel`) "
										+ "VALUES (NULL, '" +clientID + "', '"+roomID+"', '"+ resa.getIn()+"', '"+ resa.getOut()+"', '"+ price +"', '" + hotelID+ "')"
										); // A FINIR
								preparedStmt.execute();
								
								// Update solde client
								preparedStmt = con.prepareStatement(
										"UPDATE `CreditCard` SET `Amount` = '" + (client.getCc().getAmount()) + "' WHERE `CreditCard`.`Client` =" + clientID); // A FINIR
								preparedStmt.execute();
							}
							catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println("Your order have been placed. Thank you for your purchase !\n");
							getRecipe(hotel, client, resa);
							makePdf(agency, hotel, client, resa);
						}
						else {
							System.err.println("Please verify your account balance.");
							System.err.println("Problem during your reservation please try again.");
							return;
						}
						
					}
					else {
						System.err.println("Purchase aborted...");
						return;
					}
				}
			}
			else {
				System.out.println("Card number : ");
				String num = textScanner.nextLine();
				System.out.println("CVV number : ");
				String cvv = textScanner.nextLine();
				System.out.println("Expiration date (yyyy-mm-dd) : ");
				LocalDate exp = LocalDate.parse(textScanner.nextLine());
				resa = new Reservation(client, in, out, room);
				hotel.getResa().add(resa);
				System.out.println("Your order have been placed. Thank you for your purchase !\n");
				getRecipe(hotel, client, resa);
				makePdf(agency, hotel, client, resa);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int makeReservationGUI(Agency agency, Client client, LocalDate in, LocalDate out, Room room, Hotel hotel, double amount) throws ReservationException {
		Reservation resa = null;
		try {
				CreditCard cc = client.getCc();
				if(cc == null || cc.getCvv() == "000") {
					return 0;
				}
				else {
						double creditCardBalance = client.getCc().getAmount();
						double price = room.getPrice() - (room.getPrice() / 100) * amount;
						if(creditCardBalance >= price) {
							client.subMoney(price);
							resa = new Reservation(client, in, out, room);
							hotel.getResa().add(resa);
							try{  
								Class.forName("com.mysql.jdbc.Driver");
								Connection con=DriverManager.getConnection(
								"jdbc:mysql://dakota.o2switch.net:3306/sc1samo7154_hotelfinderdb","sc1samo7154_hotelupdate","hotelupdate34");
								Statement stmt=con.createStatement();
								ResultSet rs = stmt.executeQuery("SELECT ID FROM Client WHERE "
										+ "Name='"+client.getName() +"' AND Firstname='" + client.getFirstname()+"'");
								int clientID = 0; 
								if(rs.next()) {
									clientID = rs.getInt("ID");
								}
								
								rs = stmt.executeQuery("SELECT ID FROM Hotel WHERE Name='"+hotel.getName()+"'");
								int hotelID = 0;
								if(rs.next()) {
									hotelID = rs.getInt("ID");
								}
								
								rs = stmt.executeQuery("SELECT ID FROM Room WHERE Number="+ room.getRoomNumber()+" AND Hotel="+hotelID);
								int roomID = 0; 
								if(rs.next()) {
									roomID = rs.getInt("ID");
								}

								PreparedStatement preparedStmt = con.prepareStatement(
										"INSERT INTO `Reservation` (`ID`, `Client`, `Room`, `DateIn`, `DateOut`, `Price`, `Hotel`) "
										+ "VALUES (NULL, '" +clientID + "', '"+roomID+"', '"+ resa.getIn()+"', '"+ resa.getOut()+"', '"+ price +"', '" + hotelID+ "')"
										); 
								preparedStmt.execute();
								
								// Update solde client
								preparedStmt = con.prepareStatement(
										"UPDATE `CreditCard` SET `Amount` = '" + (client.getCc().getAmount()) + "' WHERE `CreditCard`.`Client` =" + clientID); // A FINIR
								preparedStmt.execute();
								
								makePdf(agency, hotel, client, resa);
							}
							catch (Exception e) {
								e.printStackTrace();
							}
							return 1;
						} else {return 0;}
				}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void viewAll(Agency agency, Client client) {
		String choice = "n";
		try {
			Scanner textScanner = new Scanner(System.in);
			HashMap<Hotel, Double> hotels = new HashMap<>();
			HashMap<HotelService, Double> proxy = agency.getOffers();
			for (Entry<HotelService, Double> prox : proxy.entrySet()) {
				HotelService hotel = prox.getKey();
					Hotel results = agency.searchRoom(hotel, "1970-06-01", "1970-06-01", 1, 0, 100000000);
					if(!results.getRooms().isEmpty()) {
						for (Room room : results.getRooms()) {
							room.setPrice(room.getPrice() - (room.getPrice() / 100 ) * agency.getOffers().get(hotel));
						}
						hotels.put(results, prox.getValue());						
					}
			}
			int cpt = 1;
			for (Entry<Hotel, Double> prox : hotels.entrySet()) {
				Hotel hotel = prox.getKey();
				double bestPrice = -1;
				for(Room room : hotel.getRooms()) {
					if (bestPrice == -1) {
						bestPrice = room.getPrice();
					}
					else if(room.getPrice() < bestPrice) {
						bestPrice = room.getPrice();
					}
				}
				double value = Double.parseDouble(new DecimalFormat("##.##").format(bestPrice));
				String valueString = String.valueOf(value);
				String starsString = String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(hotel.getStars())));
				System.out.println(hotel.getName() + " " + starsString + "\n" + hotel.getAddress().toString());
				System.out.println("Starting at "+ valueString + "€ !\n");
				System.out.println("Tap [y] to see the hotel in pictures (anything else to continue)");
				choice = textScanner.nextLine();
				if(choice.equals("y")) {
					String image = hotel.getImageFolder();
					if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
					    Desktop.getDesktop().browse(new URI(image));
					}
					else {
						System.err.println("Browsing is not supported. here is the link to see the pictures :\n"+ image);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void getRecipe(Hotel hotel, Client client, Reservation resa) {
		int size = 31;  
		System.out.println(Reservation.formRecipe(size, "Client Infos"));
		System.out.println("|                             |");
		System.out.println(Reservation.adaptiveDisplay("info", client.getFirstname(), size));
		System.out.println(Reservation.adaptiveDisplay("info", client.getName(), size));
		System.out.println(Reservation.adaptiveDisplay("info", client.getTelNumber(), size));
		System.out.println("|                             |");
		System.out.println(Reservation.formRecipe(size, "Reservation Infos"));
		System.out.println("|                             |");
		System.out.println(Reservation.adaptiveDisplay("hotelName", hotel.getName(), size));
		System.out.println(Reservation.adaptiveDisplay("room", String.valueOf(resa.getRoom().getRoomNumber()), size));
		System.out.println(Reservation.adaptiveDisplay("datein", String.valueOf(resa.getIn()), size));
		System.out.println(Reservation.adaptiveDisplay("dateout", String.valueOf(resa.getOut()), size));
		System.out.println(Reservation.adaptiveDisplay("price", String.valueOf(Double.parseDouble(new DecimalFormat("##.##").format(resa.getRoom().getPrice())))+"€", size));
		System.out.println(Reservation.formRecipe(size, "footer"));
		System.out.println("A PDF have been sent to " + System.getProperty("user.dir") +"/Reservation.pdf");
	}
	
	public static void makePdf(Agency agency, Hotel hotel, Client client, Reservation resa) {
		try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FirstPdf.FILE));
            document.open();
            FirstPdf.addMetaData(document);
            FirstPdf.addTitlePage(document, agency, hotel, client, resa);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
