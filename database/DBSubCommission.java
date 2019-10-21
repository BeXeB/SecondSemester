package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.CommissionLine;
import model.SubCommission;

public class DBSubCommission implements IDBSubCommission{
	private DBCommissionLine dbCommissionLine = new DBCommissionLine();

	@Override
	public void insertIntoDataBase(SubCommission sc, int cId) throws Exception {
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "insert into subCommission (description, deadline, commissionID) values (?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(select, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, sc.getDescription());
		pstmt.setString(2, sc.getDeadLine());
		pstmt.setInt(3, cId);
		int scId = DBConnection.getInstance().executeInsertWithIdentity(pstmt);
		for (CommissionLine cl : sc.getCls()) {
			dbCommissionLine.insertIntoDataBase(cl, scId);
		}
	}
}
