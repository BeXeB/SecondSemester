package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Material;

public class DBMaterial implements IDBMaterial{

	@Override
	public Material findById(int id) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from materials where id = ?";
		PreparedStatement pstmt = connection.prepareStatement(select);
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return buildObject(rs);
	}


	@Override
	public ArrayList<Material> findAll() throws SQLException {
		ArrayList<Material> materials = new ArrayList<Material>();
		Connection connection = DBConnection.getInstance().getDBcon();
		String select = "select * from materials";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(select);
		materials = buildObjects(rs);
		return materials;
	}
	
	private ArrayList<Material> buildObjects(ResultSet rs) throws SQLException {
		ArrayList<Material> materials = new ArrayList<Material>();
		while (rs.next()) {
			Material material = buildObject(rs);
			materials.add(material);
		}
		return materials;
	}
	
	private Material buildObject(ResultSet rs) throws SQLException {
		String name = rs.getString("mname");
		String description = rs.getString("mdescription");
		double price = rs.getDouble("price");
		int materialNo = rs.getInt("id");
		return new Material(name, description, price, materialNo);
	}


	public void insertIntoDataBase(Material m) throws SQLException {
		Connection connection = DBConnection.getInstance().getDBcon();
		String insertString = "insert into materials (mname, price, mdescription) values (?,?,?)";
		PreparedStatement pstmt = connection.prepareStatement(insertString);
		pstmt.setString(1, m.getName());
		pstmt.setDouble(2, m.getPrice());
		pstmt.setString(3, m.getDescription());
		DBConnection.getInstance().startTransaction();
		pstmt.executeUpdate();
		DBConnection.getInstance().commitTransaction();
	}

}
