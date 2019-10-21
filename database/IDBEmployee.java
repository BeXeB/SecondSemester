package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.*;

public interface IDBEmployee {
	public Employee findById(int id) throws SQLException;
	public ArrayList<Employee> findAll() throws SQLException;

}
