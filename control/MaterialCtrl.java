package control;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DBMaterial;
import model.Material;

public class MaterialCtrl {
	private DBMaterial dbMaterial;
	private Material currentMaterial;
	
	public MaterialCtrl() {
		this.dbMaterial = new DBMaterial();
	}
	
	public Material createMaterial(String name, String description, double price) throws SQLException 
	{
		currentMaterial = new Material(name, description, price);
		dbMaterial.insertIntoDataBase(currentMaterial);
		return currentMaterial;
	}

	public ArrayList<Material> findAll() throws SQLException
	{
		return dbMaterial.findAll();
	}
	
	public Material findByNo(int no) throws SQLException 
	{
		return dbMaterial.findById(no);
	}
}
