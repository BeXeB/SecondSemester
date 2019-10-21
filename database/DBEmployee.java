package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Employee;

public class DBEmployee implements IDBEmployee {
	private DBCity dbCity;
	
	public DBEmployee() {
		dbCity = new DBCity();
	}
	
	@Override
	public Employee findById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from employees where id = ?";
		PreparedStatement pstmt = connection.prepareStatement(select);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return buildObject(rs);
	}
	
	@Override
	public ArrayList<Employee> findAll() throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from employees";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		employees = buildObjects(rs);
		return employees;
	}

	private ArrayList<Employee> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		while (rs.next()) {
			Employee employee = buildObject(rs);
			employees.add(employee);
		}
		return employees;
	}

	private Employee buildObject(ResultSet rs) throws SQLException {
		String name = rs.getString("firstName") + " " + rs.getString("lastName");
		String address = rs.getString("caddress");
		int zipcode = rs.getInt("zipcode");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		boolean isManager = rs.getBoolean("isManager");
		int empNo = rs.getInt("id");
		String city = dbCity.findCityByZipcode(zipcode);
		return new Employee(name, address, city, zipcode, email, phone, isManager, empNo);
	}

	public void insertIntoDataBase(Employee e) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String insertString = "insert into employees (firstName, lastName, caddress, zipcode, email, phone, isManager) values (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(insertString);
		pstmt.setString(1, e.getName().split(" ")[0]);
		pstmt.setString(2, e.getName().split(" ")[1]);
		pstmt.setString(3, e.getAddress());
		pstmt.setInt(4, e.getZipcode());
		pstmt.setString(5, e.getEmail());
		pstmt.setString(6, e.getPhone());
		pstmt.setBoolean(7, e.isManager());
		DBConnection.getInstance().startTransaction();
		pstmt.executeUpdate();
		DBConnection.getInstance().commitTransaction();
	}


}
