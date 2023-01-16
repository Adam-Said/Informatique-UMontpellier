
package webservice;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hotel complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hotel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="address" type="{http://service/}position" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="resa" type="{http://service/}reservation" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="rooms" type="{http://service/}room" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="stars" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="imageFolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hotel", propOrder = {
    "address",
    "name",
    "resa",
    "rooms",
    "stars",
    "imageFolder"
})
public class Hotel {

    protected Position address;
    protected String name;
    @XmlElement(nillable = true)
    protected List<Reservation> resa;
    @XmlElement(nillable = true)
    protected List<Room> rooms;
    protected double stars;
    protected String imageFolder;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Position }
     *     
     */
    public Position getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Position }
     *     
     */
    public void setAddress(Position value) {
        this.address = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }
    
    
    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Room }
     *     
     */
    public void setRoom(ArrayList<Room> value) {
        this.rooms = value;
    }

    /**
     * Gets the value of the resa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reservation }
     * 
     * 
     */
    public List<Reservation> getResa() {
        if (resa == null) {
            resa = new ArrayList<Reservation>();
        }
        return this.resa;
    }

    /**
     * Gets the value of the rooms property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rooms property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRooms().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Room }
     * 
     * 
     */
    public List<Room> getRooms() {
        if (rooms == null) {
            rooms = new ArrayList<>();
        }
        return this.rooms;
    }

    /**
     * Gets the value of the stars property.
     * 
     */
    public double getStars() {
        return stars;
    }

    /**
     * Sets the value of the stars property.
     * 
     */
    public void setStars(double value) {
        this.stars = value;
    }
    
    /**
     * Gets the value of the imageFolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageFolder() {
        return imageFolder;
    }

    /**
     * Sets the value of the imageFolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageFolder(String value) {
        this.imageFolder = value;
    }
    
	@Override
	public String toString() {
		return "Hotel "+ this.getName() +"\n Rating : " + Double.parseDouble(new DecimalFormat("##.##").format(this.getStars())) + "\n" + this.getAddress().toString() + "\n" + roomsToString();
	}
	
	public String roomsToString() {
		String res = "";
		for (Room room : this.getRooms()) {
			res += room.toString();
		}
		return res;
	}

}
