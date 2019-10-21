package control;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DBEmployee;
import model.Employee;

public class EmployeeCtrl {
	private DBEmployee dbEmployee;
	private Employee currentEmployee;
	
	public EmployeeCtrl() {
		this.dbEmployee = new DBEmployee();
	}
	
	public Employee createEmployee(String name, String address, int zipCode, String email, String phone, boolean isBusiness) throws SQLException 
	{
		currentEmployee = new Employee(name, address, zipCode, email, phone, isBusiness);
		dbEmployee.insertIntoDataBase(currentEmployee);
		return currentEmployee;
	}

	public ArrayList<Employee> findAll() throws SQLException {
		return dbEmployee.findAll();
	}

	public Employee findByNo(int no) throws SQLException {
		return dbEmployee.findById(no);
	}
}
