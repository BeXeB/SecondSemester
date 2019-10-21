package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

import model.Employee;
import model.Group;

public class DBGroup implements IDBGroup{
	
	@Override
	public Group findById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from groups where id = ?";
		PreparedStatement pstmt = connection.prepareStatement(select);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return buildObject(rs);	
	}

	@Override
	public ArrayList<Group> findAll() throws SQLException {
		ArrayList<Group> groups = new ArrayList<Group>();
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from groups";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		groups = buildObjects(rs);
		return groups;
	}

	private ResultSet findEmployeesInGroup(int gId) throws SQLException 
	{
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select employeeID, ehours from employeesInGroup where groupID = ?";
		PreparedStatement pstmt = connection.prepareStatement(select);
		pstmt.setInt(1, gId);
		return pstmt.executeQuery();
	}
	
	private Group buildObject(ResultSet rs) throws SQLException 
	{
		String description = rs.getString("gdescription");
		int groupNo = rs.getInt("id");
		Group group = new Group(description, groupNo);
		ResultSet empWH = findEmployeesInGroup(groupNo);
		DBEmployee dbEmp = new DBEmployee();
		while (empWH.next()) {
			Employee employee = dbEmp.findById(empWH.getInt("employeeID"));
			int hours = empWH.getInt("ehours");
			group.addEmployee(employee, hours);
		}
		return group;
	}
	
	private ArrayList<Group> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Group> groups = new ArrayList<Group>();
		while (rs.next()) {
			Group group = buildObject(rs);
			groups.add(group);
		}
		return groups;
	}

	public void insertIntoDataBase(Group g) throws Exception {
		Connection connection = DBConnection.getInstance().getDBcon();
		String insertString = "insert into groups (gdescription) values (?)";
		PreparedStatement pstmt = connection.prepareStatement(insertString, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, g.getDescription());
		DBConnection.getInstance().startTransaction();
		int gId = DBConnection.getInstance().executeInsertWithIdentity(pstmt);
		for (Employee employee : g.getHoursOfWork().keySet()) {
			insertString = "insert into employeesInGroup (groupID, employeeID, ehours) values (?,?,?)";
			pstmt = connection.prepareStatement(insertString);
			pstmt.setInt(1, gId);
			pstmt.setInt(2, employee.getEmpNo());
			pstmt.setInt(3, g.getHoursOfWork().get(employee));
			pstmt.execute();
		}
		DBConnection.getInstance().commitTransaction();
	}
}