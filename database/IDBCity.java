package database;

import java.sql.SQLException;

public interface IDBCity {
	public String findCityByZipcode(int zipcode) throws SQLException;
}
