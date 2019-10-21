package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.CommissionLine;

public class DBCommissionLine implements IDBCommissionLine{

	@Override
	public void insertIntoDataBase(CommissionLine cl, int scId) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String insert = "insert into commissionLine (quantity, totalPrice, subCommissionID, materialID) values (?,?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(insert);
		pstmt.setInt(1, cl.getQuantity());
		pstmt.setDouble(2, cl.getQuantity() * cl.getMaterial().getPrice());
		pstmt.setInt(3, scId);
		pstmt.setInt(4, cl.getMaterial().getMaterialNo());
		pstmt.executeUpdate();
	}

}
