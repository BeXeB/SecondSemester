package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCity implements IDBCity{

	@Override
	public String findCityByZipcode(int zipcode) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select name from city where zipcode = ?";
		PreparedStatement pstmt = connection.prepareStatement(select);
		pstmt.setInt(1, zipcode);
		ResultSet rSet = pstmt.executeQuery();
		rSet.next();
		return rSet.getString("name");
	}
	
}
