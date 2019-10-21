package control;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DBGroup;
import model.Employee;
import model.Group;

public class GroupCtrl {
	private DBGroup dbGroup;
	
	private EmployeeCtrl employeeCtrl;
	
	private Group currentGroup;
	
	public GroupCtrl() {
		dbGroup = new DBGroup();
		employeeCtrl = new EmployeeCtrl();
	}
	
	public Group createGroup(String description) 
	{
		currentGroup = new Group(description);
		return currentGroup;
	}
	
	public ArrayList<Employee> getEmployees() throws SQLException
	{
		return employeeCtrl.findAll();
	}
	
	public void addEmployee (int no, int hours) throws SQLException 
	{
		currentGroup.addEmployee(employeeCtrl.findByNo(no), hours);
	}

	public ArrayList<Group> findAll() throws SQLException
	{
		return dbGroup.findAll();
	}
	
	public Group findByNo(int no) throws SQLException 
	{
		return dbGroup.findById(no);
	}
	
	public void resetGroup() 
	{
		this.currentGroup = null;
	}
	
	public void saveToDataBase() throws Exception {
		dbGroup.insertIntoDataBase(currentGroup);
	}
}
