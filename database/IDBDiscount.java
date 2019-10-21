package database;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Discount;

public interface IDBDiscount {
	public Discount findById(int id) throws SQLException;
	public ArrayList<Discount> findAll() throws SQLException;
}
