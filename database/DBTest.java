package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
	private final String SELECT = "SELECT 1";
	private Statement statement;
	
	public DBTest() {
		try {
			this.statement =  DBConnection.getInstance().getDBcon().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String doTest() 
	{
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(SELECT);
		} catch (SQLException e) {
			System.err.println("Timed out");
			return "Disconnected";
		}
		if (!resultSet.equals(null)) {
			System.err.println("Connected");
			return "Connected";
		}
		else
		{
			System.err.println("Disconnected");
			return "Disconnected";
		}
	}
}
