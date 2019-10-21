package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Material;;

public interface IDBMaterial {
	public Material findById(int id) throws SQLException;
	public ArrayList<Material> findAll() throws SQLException;
}
