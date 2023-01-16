package model;

public class Client {
	private String name;
	private String firstname;
	private String telNumber;
	private int age;
	private CreditCard cc;
	
	public CreditCard getCc() {
		return cc;
	}

	public void setCc(CreditCard cc) {
		this.cc = cc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void addMoney(double amount) {
		this.cc.addMoney(amount);
	}

	public void subMoney(double amount) {
		this.cc.subMoney(amount);
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

	@Override
	public String toString() {
		return name + " " + firstname + "\n" + age + "\n" + telNumber+ "\n" + "CC: " 
				+ this.getCc().cardToString();
	}
	
	public String infoToString() {
		return name + " " + firstname + "\n";
	}
	

}
