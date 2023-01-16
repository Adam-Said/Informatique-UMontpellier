
package webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;




/**
 * <p>Java class for client complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="client"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="cc" type="{http://service/}creditCard" minOccurs="0"/&gt;
 *         &lt;element name="firstname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="telNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "client", propOrder = {
    "age",
    "cc",
    "firstname",
    "name",
    "telNumber"
})
public class Client {

    protected int age;
    protected CreditCard cc;
    protected String firstname;
    protected String name;
    protected String telNumber;

    /**
     * Gets the value of the age property.
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

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
     * Gets the value of the firstname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the value of the firstname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstname(String value) {
        this.firstname = value;
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
     * Gets the value of the telNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * Sets the value of the telNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelNumber(String value) {
        this.telNumber = value;
    }
    
	public void addMoney(double amount) {
		this.cc.addMoney(amount);
	}

	public void subMoney(double amount) {
		this.cc.subMoney(amount);
	}

	@Override
	public String toString() {
		return name + " " + firstname + "\n" + age + "\n" + telNumber+ "\n" + "CC: " 
				+ this.getCc().cardToString();
	}
	
	public String infoToString() {
		return name + " " + firstname + "\n";
	}
	
	public Client(String name, String firstname, String telNumber, int age, CreditCard cc) {
		this.name = name;
		this.firstname = firstname;
		this.telNumber = telNumber;
		this.age = age;
		this.cc = cc;
	}

	public Client(String name, String firstname, String telNumber, int age) {
		this.name = name;
		this.firstname = firstname;
		this.telNumber = telNumber;
		this.age = age;
		this.cc = new CreditCard();
	}

	public Client() {
		this.name = "XXX";
		this.firstname = "XXXX";
		this.telNumber = "0000000000";
		this.age = 0;
		this.cc = null;
	}

}
