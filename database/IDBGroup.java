package database;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Group;

public interface IDBGroup {
	public Group findById(int id) throws SQLException;
	public ArrayList<Group> findAll() throws SQLException;
	public void insertIntoDataBase(Group g) throws Exception;
}
