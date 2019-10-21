package database;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Commission;
import model.Group;
import model.SubCommission;

public class DBCommission implements IDBCommission{
	private DBSubCommission dbSubCommission = new DBSubCommission();
	
	@Override
	public void insertIntoDataBase(Commission c) throws Exception {

		Connection connection = DBConnection.getInstance().getDBcon();
		String insertString = "insert into commissions (odate, deadline, paymentDate, price, customerID, discountID) values (?,?,?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(insertString, PreparedStatement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, c.getDate());
		pstmt.setString(2, c.getDeadLine());
		pstmt.setString(3, c.getDateOfPayment());
		pstmt.setDouble(4, c.getPrice());
		pstmt.setInt(5, c.getClient().getClientNo());
		pstmt.setInt(6, c.getDiscount().getDiscountNo());
		DBConnection.getInstance().startTransaction();
		int cId = DBConnection.getInstance().executeInsertWithIdentity(pstmt);
		for (SubCommission sc : c.getScs()) {
			dbSubCommission.insertIntoDataBase(sc, cId);
		}
		for (Group g : c.getGroups()) {
			insertString = "insert into groupsOnCommission (commissionID, groupID, gfrom, gto) values (?,?,?,?)";
			pstmt = connection.prepareStatement(insertString);
			pstmt.setInt(1, cId);
			pstmt.setInt(2, g.getGroupNo());
			pstmt.setString(3, g.getFrom());
			pstmt.setString(4, g.getTo());
			pstmt.execute();
		}
		DBConnection.getInstance().commitTransaction();
	}
}
