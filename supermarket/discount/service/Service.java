package supermarket.discount.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import supermarket.discount.model.Discount;
import supermarket.util.DbUtil;

public class Service {
	
	private static final String SELECT_DISCOUNTS = "SELECT * FROM discounts";
	private static final String SELECT_DISCOUNT_BY_PRODUCT_NAME = "SELECT * FROM discounts WHERE productName = ?";
	private static final String UPDATE_DISCOUNT = "UPDATE discounts SET quantity = ?, discount = ? WHERE productID = ?";
	private static final String NEW_DISCOUNT = "INSERT INTO discounts(productID,quantity,discount,productName) VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE quantity = ?,discount = ?";
	private static final String DELETE_DISCOUNT = "DELETE FROM discounts WHERE productID = ?";
	
	public static ArrayList<Discount> getDiscounts(){
		Connection connection = DbUtil.getConnection();
		ArrayList<Discount> discounts = new ArrayList<Discount>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISCOUNTS);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Discount discount = new Discount();
				int quantity = rs.getInt("quantity");
				int discountP = rs.getInt("discount");
				int productID = rs.getInt("productID");
				String name = rs.getString("productName");
				discount.setProductID(productID);
				discount.setDiscount(discountP);
				discount.setQuantity(quantity);
				discount.setProductName(name);
				discounts.add(discount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return discounts;
	}
	public static ArrayList<Discount> getDiscountsByProductName(String productName){
		Connection connection = DbUtil.getConnection();
		ArrayList<Discount> discounts = new ArrayList<Discount>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISCOUNT_BY_PRODUCT_NAME);
			preparedStatement.setString(1, productName);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Discount discount = new Discount();
				int quantity = rs.getInt("quantity");
				int discountP = rs.getInt("discount");
				int productID = rs.getInt("productID");
				String name = rs.getString("productName");
				discount.setProductID(productID);
				discount.setDiscount(discountP);
				discount.setQuantity(quantity);
				discount.setProductName(name);
				discounts.add(discount);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return discounts;
	}
	public static void updateDiscount(int productID,int quantity,int discount) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISCOUNT);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, discount);
			preparedStatement.setInt(3, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void newDiscount(int productID,int quantity,int discount,String productName) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_DISCOUNT);
			preparedStatement.setInt(1, productID);
			preparedStatement.setInt(2, quantity);
			preparedStatement.setInt(3, discount);
			preparedStatement.setString(4, productName);
			preparedStatement.setInt(5, quantity);
			preparedStatement.setInt(6, discount);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void deleteDiscount(int productID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DISCOUNT);
			preparedStatement.setInt(1, productID);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
