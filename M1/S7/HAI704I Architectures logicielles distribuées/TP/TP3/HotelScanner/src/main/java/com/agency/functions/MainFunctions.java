package com.agency.functions;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;

import com.agency.exceptions.ReservationException;
import com.agency.models.FirstPdf;
import com.agency.models.Hotel;
import com.agency.models.Reservation;
import com.agency.models.Room;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

public class MainFunctions {
	
	@SuppressWarnings("unused")
	public static Reservation makeReservation(BufferedReader reader, LocalDate in, LocalDate out, Room room, Hotel hotel, double amount) throws ReservationException {
		Reservation resa = null;
		try {
			System.out.println("Firstname : ");
			String firstname = reader.readLine();
			System.out.println("Name : ");
			String name = reader.readLine();
			System.out.println("E-mail : ");
			String mail = reader.readLine();
			System.out.println("Phone number : ");
			String phone = reader.readLine();
			System.out.println("Card number : ");
			String num = reader.readLine();
			System.out.println("CVV number : ");
			String cvv = reader.readLine();
			System.out.println("Expiration date (yyyy-mm-dd) : ");
			LocalDate exp = LocalDate.parse(reader.readLine());
			resa = new Reservation(firstname + " " + name, in, out, amount, room, hotel);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resa;
	}
	
	
	public static void getRecipe(Hotel hotel, String client, Reservation resa) {
		int size = 31;  
		System.out.println(Reservation.formRecipe(size, "Client Infos"));
		System.out.println("|                             |");
		System.out.println(Reservation.adaptiveDisplay("info", client, size));
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
	
	public static void makePdf(Hotel hotel, String client, Reservation resa) {
		try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FirstPdf.FILE));
            document.open();
            FirstPdf.addMetaData(document);
            FirstPdf.addTitlePage(document, hotel, client, resa);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
}
