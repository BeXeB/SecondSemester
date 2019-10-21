package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Discount;

public class DBDiscount implements IDBDiscount{

	@Override
	public Discount findById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from discount where id = ?";
		PreparedStatement pstmt = connection.prepareStatement(select);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return buildObject(rs);
	}

	@Override
	public ArrayList<Discount> findAll() throws SQLException {
		ArrayList<Discount> discounts = new ArrayList<Discount>();
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from discount";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		discounts = buildObjects(rs);
		return discounts;
	}
	
	private ArrayList<Discount> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Discount> discounts = new ArrayList<Discount>();
		while (rs.next()) {
			Discount discount = buildObject(rs);
			discounts.add(discount);
		}
		return discounts;
	}
	
	private Discount buildObject(ResultSet rs) throws SQLException {
		int percentage = rs.getInt("dpercentage");
		int discountNo = rs.getInt("id");
		return new Discount(percentage, discountNo);
	}

	public void insertIntoDataBase(Discount d) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String insertString = "insert into discount (dpercentage) values (?)";
		PreparedStatement pstmt = connection.prepareStatement(insertString);
		pstmt.setInt(1, d.getPercentage());
		DBConnection.getInstance().startTransaction();
		pstmt.executeUpdate();
		DBConnection.getInstance().commitTransaction();
	}
}
