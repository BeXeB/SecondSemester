package model;

public class Employee {
 	private String name;
	private String address;
	private String city;
	private int zipcode;
	private String email;
	private String phone;
	private boolean isManager;
	private int empNo;
	
	public Employee(String name, String address, int zipcode, String email, String phone, boolean isManager) {
 		setName(name);
 		setAddress(address);
 		setZipcode(zipcode);
 		setEmail(email);
 		setPhone(phone);
 		setManager(isManager);
 	}
	
	public Employee(String name, String address, String city, int zipcode, String email, String phone, boolean isManager, int empNo) 
 	{
 		setName(name);
 		setAddress(address);
 		setCity(city);
 		setZipcode(zipcode);
 		setEmail(email);
 		setPhone(phone);
 		setManager(isManager);
 		setEmpNo(empNo);
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

	public int getZipcode() {
		return zipcode;
	}

	private void setZipcode(int zipcode) {
		this.zipcode = zipcode;
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

	public boolean isManager() {
		return isManager;
	}

	private void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	public int getEmpNo() {
		return empNo;
	}

	private void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String toString() 
	{
		return "Name: " + name + ", Address: " + address + ", City: " + city + ", Zip Code: " + zipcode + ", E-mail: " + email + ", Phone: " + phone + ", Manager: " + isManager + ", Employee No: " + empNo;
	}
}
