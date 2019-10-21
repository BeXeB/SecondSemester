package model;

import java.util.LinkedList;

public class SubCommission {
	private String description;
	private String deadLine;
	private LinkedList<CommissionLine> cls;
	
	public SubCommission(String description, String deadLine) {
		cls = new LinkedList<CommissionLine>();
		setDescription(description);
		setDeadLine(deadLine);
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public String getDeadLine() {
		return deadLine;
	}

	private void setDeadLine(String deadLine) {
		this.deadLine = deadLine.replace(' ', 'T');
	}

	public LinkedList<CommissionLine> getCls() {
		return cls;
	}

	@SuppressWarnings("unused")
	private void setCls(LinkedList<CommissionLine> cls) {
		this.cls = cls;
	}
	
	public void addCl(CommissionLine cl) 
	{
		this.cls.add(cl);
	}
	public double calculatePrice() 
	{
		double price = 0;
		for (CommissionLine commissionLine : cls) {
			price += commissionLine.getMaterial().getPrice() * commissionLine.getQuantity();
		}
		return price;
	}
	public String toString() 
	{
		String string = "";
		string += "Description: " + description + ", Deadline: " + deadLine + "\n\tMaterials:";
		for (CommissionLine commissionLine : cls) {
			string += "\n\t\t" + commissionLine.toString();
		}
		return string;
	}
}
