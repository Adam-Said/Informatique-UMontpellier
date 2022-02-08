package fr.umfds.gestionter;


import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.FixedUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;

import java.net.SocketException;

import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;

public class HelloWorldICal4J {
	public static void main(String[] args) throws SocketException {
		// Code extrait de la documentation de ICal4J
		
		net.fortuna.ical4j.model.Calendar cal = new net.fortuna.ical4j.model.Calendar();
		cal.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
		cal.getProperties().add(Version.VERSION_2_0);
		cal.getProperties().add(CalScale.GREGORIAN);

		// Add events, etc..



		java.util.Calendar calendar = java.util.Calendar.getInstance();
		calendar.set(java.util.Calendar.MONTH, java.util.Calendar.DECEMBER);
		calendar.set(java.util.Calendar.DAY_OF_MONTH, 25);

		// initialise as an all-day event..
		VEvent christmas = new VEvent(new Date(calendar.getTime()), "Christmas Day");

		// Generate a UID for the event..
		UidGenerator ug = new FixedUidGenerator("1");
		christmas.getProperties().add(ug.generateUid());

		
		cal.getComponents().add(christmas);
	}
}