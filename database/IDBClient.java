package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Client;

public interface IDBClient {
	public Client findById(int id) throws SQLException;
	public ArrayList<Client> findAll() throws SQLException;
}