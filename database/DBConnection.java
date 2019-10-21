package database;

import java.sql.*;

public class DBConnection {
	//private static final String URL = "jdbc:sqlserver://kraka.ucn.dk:1433; user = dmai0918_1074291; password = Password1!";
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String serverAddress = "kraka.ucn.dk";
	private static final int    serverPort = 1433;
	private static final String userName = "dmai0918_1074291";
	private static final String password = "Password1!";
	private Connection connection = null;
	private static DBConnection instance = null;

	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	private DBConnection() {
		String connectionString = String.format("jdbc:sqlserver://%s:%d;user=%s;password=%s", 
				serverAddress, serverPort, userName, password);
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.err.println("Could not connect to database @" + serverAddress + ":" + serverPort + " as user " + userName + " using password ******");
			System.out.println("Connection string was: " + connectionString.substring(0, connectionString.length() - password.length()) + "....");
			e.printStackTrace();
		}
	}

	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}

	public void commitTransaction() throws SQLException {
		connection.commit();
		connection.setAutoCommit(true);
	}

	public void rollbackTransaction() throws SQLException {
		connection.rollback();
		connection.setAutoCommit(true);
	}
	
	public int executeInsertWithIdentity(PreparedStatement ps) throws Exception {
		int res = -1;
		try {
			res = ps.executeUpdate();
			if (res > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	public Connection getDBcon() {
		return connection;
	}

	public void closeConnection() {
		try {
			connection.close();
			instance = null;
			System.err.println("The connection is closed");
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
	}
}
