package model;

import java.util.LinkedList;

public class Commission {
	private String date;																	//Fields | Variables
	private String deadLine;																//Private: Only this class
	private String dateOfPayment;															//Public: every class
	private double price;																	//Protected: This class and its child classes
	private Discount discount;		
	private Client client;		
	private LinkedList<SubCommission> scs;	
	private LinkedList<Group> groups;	

	public Commission(String date, String deadLine, String dateOfPayment, double price) {	//Constructor
		scs = new LinkedList<SubCommission>();												//Called when we create a new object from
		groups = new LinkedList<Group>();													//this Class using the new operator/keyword
		setDate(date);												
		setDeadLine(deadLine);														
		setDateOfPayment(dateOfPayment);										
		setPrice(price);												
	}						
	
	public String getDate() {																//Getters & Setters
		return date;																		//We use these to access | modify the
	}																						//data in the fields
	private void setDate(String date) {	
		this.date = date.replace(' ', 'T');	
	}
	public String getDeadLine() {
		return deadLine;
	}
	private void setDeadLine(String deadLine) {
		this.deadLine = deadLine.replace(' ', 'T');											
	}
	public String getDateOfPayment() {
		return dateOfPayment;
	}
	private void setDateOfPayment(String dateOfPayment) {
		if (dateOfPayment== null) {
			this.dateOfPayment = null;
		}
		else {
			this.dateOfPayment = dateOfPayment.replace(' ', 'T');
		}
	}
	public double getPrice() {
		return price;
	}
	private void setPrice(double price) {
		this.price = price;
	}
	public Discount getDiscount() {
		return discount;
	}
	public boolean setDiscount(Discount discount) {											
		this.discount = discount;
		return !this.discount.equals(null);
	}
	public Client getClient() {
		return client;
	}
	public boolean setClient(Client client) {
		this.client = client;
		return !this.client.equals(null);
	}
	public LinkedList<SubCommission> getScs() {
		return scs;
	}
	@SuppressWarnings("unused")
	private void setScs(LinkedList<SubCommission> scs) {
		this.scs = scs;
	}
	public boolean addSc(SubCommission sc) {
		return this.scs.add(sc);
	}
	public LinkedList<Group> getGroups() {
		return groups;
	}
	@SuppressWarnings("unused")
	private void setGroups(LinkedList<Group> groups) {
		this.groups = groups;
	}
	public boolean addGroup(Group g) 
	{
		return this.groups.add(g);
	}
	
	public void calculatePrice() 															//We use this method to update the price
	{																						//after the creation of every SubComission
		double pr = 0;
		for (SubCommission sCommission : scs) {
			pr += sCommission.calculatePrice();
		}
		int noEmployee = 0;
		for (Group group : groups) {
			noEmployee += group.getHoursOfWork().size();
		}
		pr = pr * (100-(this.getDiscount().getPercentage()))/100;
		pr += noEmployee * 1000;
		this.price = pr;
	}
	public String toString() 																//Returns the current status 
	{																						//of the object in a string
		String string = "";																	//status: the values of the fields at a given time
		string += "Date:\n\t" + date + "\nDeadline:\n\t" + deadLine + "\nDate of payment:\n\t" + dateOfPayment + "\nPrice:\n\t" + price + "\n";
		string += "Discount:\n\t" + discount.toString() + "\n";
		string += "Client:\n\t" + client.toString() + "\n";
		string += "Groups:";
		for (Group group : groups) {
			string += "\n\t" + group.toString();
		} 
		string += "\nSubCommissions:";
		for (SubCommission subCommission : scs) {
			string += "\n\t" + subCommission.toString();
		}
		return string;
	}
}
