package database;

import model.SubCommission;

public interface IDBSubCommission {
	public void insertIntoDataBase(SubCommission sc, int cId) throws Exception;
}
