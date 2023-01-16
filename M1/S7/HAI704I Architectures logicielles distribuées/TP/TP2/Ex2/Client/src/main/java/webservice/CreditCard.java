
package webservice;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;




/**
 * <p>Java class for creditCard complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="creditCard"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="cvv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="expiration" type="{http://service/}localDate" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://service/}client" minOccurs="0"/&gt;
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "creditCard", propOrder = {
    "amount",
    "cvv",
    "expiration",
    "name",
    "number"
})
public class CreditCard {

    protected double amount;
    protected String cvv;
    protected LocalDate expiration;
    protected Client name;
    protected String number;

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the cvv property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * Sets the value of the cvv property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCvv(String value) {
        this.cvv = value;
    }

    /**
     * Gets the value of the expiration property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDate }
     *     
     */
    public LocalDate getExpiration() {
        return expiration;
    }

    /**
     * Sets the value of the expiration property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDate }
     *     
     */
    public void setExpiration(LocalDate value) {
        this.expiration = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link Client }
     *     
     */
    public Client getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Client }
     *     
     */
    public void setName(Client value) {
        this.name = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumber(String value) {
        this.number = value;
    }
    
	public void addMoney(double amount) {
		this.amount += amount;
	}
	
	public void subMoney(double amount) {
		this.amount -= amount;
	}

	@Override
	public String toString() {
		return "Credit card -\nClient - " + this.name.infoToString() + 
				this.number + " " + this.cvv + " " + this.expiration + "\n" +
				"Balance : " + this.amount;
	}
	
	public String cardToString() {
		return this.number + " " + this.cvv + " " + this.expiration + "\n" +
				"Balance : " + this.amount + "€";
	}
	
	public CreditCard() {
		this.name = new Client();
		this.number = "0000 0000 0000 0000";
		this.cvv = "000";
		this.expiration = LocalDate.parse("2035-01-01");
		this.amount = 0;
	}
	
	public CreditCard(Client client, String number, String cvv, LocalDate expiration) {
		this.name = client;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.amount = 0;
	}
	

	public CreditCard(Client client, String number, String cvv, LocalDate expiration, double amount) {
		this.name = client;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.amount = amount;
	}
	
	public CreditCard(String number, String cvv, LocalDate expiration, double amount) {
		this.name = null;
		this.number = number;
		this.cvv = cvv;
		this.expiration = expiration;
		this.amount = amount;
	}

}
