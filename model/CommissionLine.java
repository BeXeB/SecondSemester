package model;

public class CommissionLine {
	private Material material;
	private int quantity;
	
	public CommissionLine(Material material, int quantity) {
		setMaterial(material);
		setQuantity(quantity);
	}

	public Material getMaterial() {
		return material;
	}

	private void setMaterial(Material material) {
		this.material = material;
	}

	public int getQuantity() {
		return quantity;
	}

	private void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String toString() 
	{
		return "Material: " + material.toString() + "\n\t\tQuantity: " + quantity;
	}
}
