package database;

import model.Commission;

public interface IDBCommission {
	public void insertIntoDataBase(Commission c) throws Exception;
}
