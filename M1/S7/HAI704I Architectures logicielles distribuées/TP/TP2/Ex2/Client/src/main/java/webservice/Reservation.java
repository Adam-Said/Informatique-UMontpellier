
package webservice;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reservation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reservation"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cc" type="{http://service/}creditCard" minOccurs="0"/&gt;
 *         &lt;element name="client" type="{http://service/}client" minOccurs="0"/&gt;
 *         &lt;element name="in" type="{http://service/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="out" type="{http://service/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="room" type="{http://service/}room" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reservation", propOrder = {
    "cc",
    "client",
    "in",
    "out",
    "room"
})
public class Reservation {

    protected CreditCard cc;
    protected Client client;
    protected LocalDate in;
    protected LocalDate out;
    protected Room room;

    /**
     * Gets the value of the cc property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCc() {
        return cc;
    }

    /**
     * Sets the value of the cc property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCc(CreditCard value) {
        this.cc = value;
    }

    /**
     * Gets the value of the client property.
     * 
     * @return
     *     possible object is
     *     {@link Client }
     *     
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the value of the client property.
     * 
     * @param value
     *     allowed object is
     *     {@link Client }
     *     
     */
    public void setClient(Client value) {
        this.client = value;
    }

    /**
     * Gets the value of the in property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getIn() {
        return in;
    }

    /**
     * Sets the value of the in property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setIn(LocalDate value) {
        this.in = value;
    }

    /**
     * Gets the value of the out property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getOut() {
        return out;
    }

    /**
     * Sets the value of the out property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setOut(LocalDate value) {
        this.out = value;
    }

    /**
     * Gets the value of the room property.
     * 
     * @return
     *     possible object is
     *     {@link Room }
     *     
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the value of the room property.
     * 
     * @param value
     *     allowed object is
     *     {@link Room }
     *     
     */
    public void setRoom(Room value) {
        this.room = value;
    }
    
	public Reservation(Client client, LocalDate in, LocalDate out, Room room) {
		this.client = client;
		this.in = in;
		this.out = out;
		this.cc = client.getCc();
		this.room = room;
	}
	
	public Reservation() {
		this.client = null;
		this.in = LocalDate.parse("2000-01-01");
		this.out = LocalDate.parse("2000-01-01");
		this.cc = new CreditCard();
		this.room = new Room();
	}
	@Override
	public String toString() {
		return "Reservation : " + client.infoToString() + "room n°" + room.getRoomNumber() + "\n"+
				"From " + in + " to " + out + "\n";
	}
	


	public static String formRecipe(int size, String element) {
		String display = "";
		String firstLine = "+";
		String middleLine = "|";
		int wSpace = size - 2;

		if(element.equals("footer")) {
			display = "+";
			for(int i = 0; i < wSpace; i++) {
				display = display + "-"; 
			}
			return display + "+" + "\n";
		}

		for(int i = 0; i < wSpace; i++) {
			firstLine = firstLine + "-";
		}
		firstLine = firstLine + "+";
		String endLine = firstLine;
		firstLine = firstLine + "\n";
		wSpace = wSpace - element.length();
		int edges = 0;

		if(wSpace % 2 == 0) {
			edges = wSpace/2;
			String separators = "";
			for(int k = 0; k < edges; k++) {
				separators = separators + " ";
			}
			middleLine = middleLine + separators + element + separators + "|" + "\n";
		} else if (wSpace % 2 != 0) {
			edges = wSpace/2;
			String separators = "";
			for(int k = 0; k < edges; k++) {
				separators = separators + " ";
			}
			middleLine = middleLine + separators + " " + element + separators + "|" + "\n";
		}

		display = firstLine + middleLine + endLine;
		return display;
	}

	public static String adaptiveDisplay(String choice, String element, int size) {
		int wSpace = 0;
		String startLine = "| ";

		if(choice.equals("hotelName")) {
			String endLine = "";
			startLine = startLine + "Hotel : ";
			wSpace = (size - 11) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("room")) {
			String endLine = "";
			startLine = startLine + "Room : ";
			wSpace = (size - 10) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if (choice.equals("datein")) {
			String endLine = "";
			startLine = startLine + "Arrival Date : ";
			wSpace = (size - 18) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("dateout")) {
			String endLine = "";
			startLine = startLine + "Departure Date : ";
			wSpace = (size - 20) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("price")) {
			String endLine = "";
			startLine = startLine + "Price : ";
			wSpace = (size - 11) - element.length();
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		} else if(choice.equals("info")) {
			wSpace = (size - 3) - element.length();
			String endLine = "";
			for(int i = 0; i < wSpace; i++) {
				endLine = endLine + " ";
			}
			endLine = endLine + "|";
			String display = startLine + element + endLine;
			return display;
		}
		return null;
	}

}
