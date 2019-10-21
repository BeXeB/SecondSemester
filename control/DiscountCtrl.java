package control;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DBDiscount;
import model.Discount;

public class DiscountCtrl {
	private DBDiscount dbDiscount;
	private Discount currentDiscount;
	
	public DiscountCtrl() {
		this.dbDiscount = new DBDiscount();
	}
	
	
	public Discount createDiscount(int percentage) throws SQLException 
	{
		currentDiscount = new Discount(percentage);
		dbDiscount.insertIntoDataBase(currentDiscount);
		return currentDiscount;
	}


	public ArrayList<Discount> findAll() throws SQLException
	{
		return dbDiscount.findAll();
	}
	
	public Discount findByNo(int no) throws SQLException 
	{
		return dbDiscount.findById(no);
	}
}
