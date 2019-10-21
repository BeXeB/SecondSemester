package database;

import java.sql.SQLException;

import model.CommissionLine;

public interface IDBCommissionLine {
	public void insertIntoDataBase(CommissionLine cl, int scId) throws SQLException;
}
