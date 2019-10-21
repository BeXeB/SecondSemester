package model;

public class Client {
	
	private String name;
	private String address;
	private String city;
	private int zipCode;
	private String email;
	private String phone;
	private boolean isBusiness;
	private int clientNo;
	
	public Client(String name, String address, String city, int zipCode, String email, String phone, boolean isBusiness, int clientNo) 
	{
		setName(name);
		setAddress(address);
		setCity(city);
		setZipCode(zipCode);
		setEmail(email);
		setPhone(phone);
		setBusieness(isBusiness);
		setClientNo(clientNo);
	}
	
	public Client(String name, String address, int zipCode, String email, String phone, boolean isBusiness) 
	{
		setName(name);
		setAddress(address);
		setZipCode(zipCode);
		setEmail(email);
		setPhone(phone);
		setBusieness(isBusiness);
	}
	
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	private void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	private void setCity(String city) {
		this.city = city;
	}
	public int getZipCode() {
		return zipCode;
	}
	private void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public String getEmail() {
		return email;
	}
	private void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	private void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean getBusieness() {
		return isBusiness;
	}
	private void setBusieness(boolean isBusieness) {
		this.isBusiness = isBusieness;
	}
	public int getClientNo() {
		return clientNo;
	}
	private void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String toString() 
	{
		return "Name: " + name + ", Address: " + address + ", City: " + city + ", Zip Code: " + zipCode + ", E-mail: " + email + ", Phone: " + phone + ", Business: " + isBusiness + ", Client No: " + clientNo;
	}
}