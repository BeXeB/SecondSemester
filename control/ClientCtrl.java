package control;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DBClient;
import model.Client;

public class ClientCtrl {
	private DBClient dbClient;
	private Client currentClient;
	
	public ClientCtrl() {
		this.dbClient = new DBClient();
	}
	
	public Client createClient(String name, String address, int zipCode, String email, String phone, boolean isBusiness) throws SQLException 
	{
		currentClient = new Client(name, address, zipCode, email, phone, isBusiness);
		dbClient.insertIntoDataBase(currentClient);
		return currentClient;
	}

	public ArrayList<Client> findAll() throws SQLException
	{
		return dbClient.findAll();
	}
	
	public Client findByNo(int no) throws SQLException 
	{
		return dbClient.findById(no);
	}
	
}
