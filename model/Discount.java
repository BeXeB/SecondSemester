package model;

public class Discount {
	private int percentage;
	private int discountNo;
	
	public Discount(int percentage) {
		setPercentage(percentage);
	}
	
	public Discount(int percentage, int discountNo) {
		setPercentage(percentage);
		setDiscountNo(discountNo);
	}

	public int getPercentage() {
		return percentage;
	}

	private void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getDiscountNo() {
		return discountNo;
	}

	private void setDiscountNo(int discountNo) {
		this.discountNo = discountNo;
	}
	public String toString() 
	{
		return "Percentage: " + percentage + ", Discount No: " + discountNo; 
	}
}
