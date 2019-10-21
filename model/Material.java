package model;

public class Material {
	private String name;
	private String description;
	private double price;
	private int materialNo;
	
	public Material(String name, String description, double price) {
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	
	public Material(String name, String description, double price, int materialNo) {
		setName(name);
		setDescription(description);
		setPrice(price);
		setMaterialNo(materialNo);
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	private void setPrice(double price) {
		this.price = price;
	}

	public int getMaterialNo() {
		return materialNo;
	}

	private void setMaterialNo(int materialNo) {
		this.materialNo = materialNo;
	}
	public String toString() 
	{
		return "Name: " + name + ", Description: " + description + ", Price: " + price + ", Material No: " + materialNo;
	}
}
