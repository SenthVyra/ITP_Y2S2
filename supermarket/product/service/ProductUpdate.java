package supermarket.product.service;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import supermarket.util.DbUtil;

public class ProductUpdate {
	private static final String UPDATE_IMAGE = "UPDATE products SET image = ? WHERE productID = ?";
	private static final String UPDATE_NAME = "UPDATE products SET productName = ? WHERE productID = ?";
	private static final String UPDATE_PRICE = "UPDATE products SET price = ? WHERE productID = ?";
	private static final String UPDATE_DESCRIPTION = "UPDATE products SET description = ? WHERE productID = ?";
	private static final String UPDATE_CATEGORY = "UPDATE products SET category = ? WHERE productID = ?";
	
	public static void updateImage(int productID,InputStream image) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_IMAGE);
			if(image!=null) {
				preparedStatement.setBlob(1, image);
			}
			preparedStatement.setInt(2, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void updateName(int productID,String name) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NAME);
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void updatePrice(int productID,BigDecimal price) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRICE);
			preparedStatement.setBigDecimal(1, price);
			preparedStatement.setInt(2, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void updateDescription(int productID,String description) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DESCRIPTION);
			preparedStatement.setString(1, description);
			preparedStatement.setInt(2, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void updateCategory(int productID,String category) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY);
			preparedStatement.setString(1, category);
			preparedStatement.setInt(2, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
