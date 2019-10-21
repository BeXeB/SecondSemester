package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Client;

public class DBClient implements IDBClient{

	@Override
	public Client findById(int id) throws SQLException {											//Queries a client with the given id
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from clients where id = ?";
		PreparedStatement pstmt = connection.prepareStatement(select);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return buildObject(rs);
	}

	@Override
	public ArrayList<Client> findAll() throws SQLException {										//Queries all of the clients
		ArrayList<Client> clients = new ArrayList<Client>();
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from clients";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		clients = buildObjects(rs);
		return clients;
	}
	
	private Client buildObject(ResultSet rs) throws SQLException {									//Creates an object from 
		String name = rs.getString("firstName") + " " + rs.getString("lastName");					//the given Result Set
		String address = rs.getString("caddress");
		int zipcode = rs.getInt("zipcode");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		boolean isBusiness = rs.getBoolean("isBusiness");
		int clientNo = rs.getInt("id");
		DBCity dbCity = new DBCity();
		String city = dbCity.findCityByZipcode(zipcode);
		return new Client(name, address, city, zipcode, email, phone, isBusiness, clientNo);
	}
	
	private ArrayList<Client> buildObjects(ResultSet rs) throws SQLException {						//Creates multiple objects from
		ArrayList<Client> clients = new ArrayList<Client>();										//the given Result Set
		while (rs.next()) {
			Client client = buildObject(rs);
			clients.add(client);
		}
		return clients;
	}

	public void insertIntoDataBase(Client c) throws SQLException {									//Receives a Client and inserts its
		Connection connection = DBConnection.getInstance().getDBcon();								//current status into the database
		String insertString = "insert into clients "
				+ "(firstName, lastName, caddress, zipcode, email, phone, isBusiness) "
				+ "values (?,?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(insertString);
		pstmt.setString(1, c.getName().split(" ")[0]);
		pstmt.setString(2, c.getName().split(" ")[1]);
		pstmt.setString(3, c.getAddress());
		pstmt.setInt(4, c.getZipCode());
		pstmt.setString(5, c.getEmail());
		pstmt.setString(6, c.getPhone());
		pstmt.setBoolean(7, c.getBusieness());
		DBConnection.getInstance().startTransaction();
		pstmt.executeUpdate();
		DBConnection.getInstance().commitTransaction();
	}
}
