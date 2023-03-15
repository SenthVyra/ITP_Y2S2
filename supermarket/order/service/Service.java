package supermarket.order.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import supermarket.order.model.*;
import supermarket.util.DbUtil;

public class Service {

	private static final String SELECT_ORDERS = "SELECT * FROM orders";
	private static final String SELECT_ORDERS_BY_ID = "SELECT * FROM orders WHERE orderID = ?";
	private static final String UPDATE_ORDER = "UPDATE orders SET quantity = ? ,status = ? WHERE orderID = ?";
	private static final String NEW_ORDER = "INSERT INTO orders(productID,productName,quantity,distributorID,status,distributorName) VALUES(?,?,?,?,?,?)";
    private static final String DELETE_ORDER = "DELETE FROM orders WHERE orderID = ?";
    private static final String ORDER_BY_QUANTITY = "SELECT productName,SUM(quantity) AS quantity FROM orders WHERE status = \"pending\" GROUP BY productName";
    private static final String ORDER_COUNT = "SELECT COUNT(orderID) AS total FROM orders";
	
    public static int orderCount() {
    	Connection connection = DbUtil.getConnection();
    	int total = 0;
    	try {
			PreparedStatement preparedStatement = connection.prepareStatement(ORDER_COUNT);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			total = rs.getInt("total");
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
    	return total;
    }
    public static JSONObject orderByQuantity() {
		Connection connection = DbUtil.getConnection();
		JSONObject json = new JSONObject();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(ORDER_BY_QUANTITY);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				String name = rs.getString("productName");
				int quantity = rs.getInt("quantity");
				try {
					json.put(name, quantity);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		return json;
	}
    
	public static ArrayList<Order> getOrders() {
		Connection connection = DbUtil.getConnection();
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				int orderID = rs.getInt("orderID");
				int productID = rs.getInt("productID");
				String productName = rs.getString("productName");
				int quantity = rs.getInt("quantity");
				int distributorID = rs.getInt("distributorID");
				String status = rs.getString("status");
				String distributorName = rs.getString("distributorName");
				order.setOrderID(orderID);
				order.setProductID(productID);
				order.setProductName(productName);
				order.setQuantity(quantity);
				order.setDistributorID(distributorID);
				order.setStatus(status);
				order.setDistributorName(distributorName);
				orders.add(order);
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
		return orders;
	}

	public static Order getOrderByID(int id) {
		Connection connection = DbUtil.getConnection();
		Order order = new Order();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int orderID = rs.getInt("orderID");
			int productID = rs.getInt("productID");
			String productName = rs.getString("productName");
			int quantity = rs.getInt("quantity");
			int distributorID = rs.getInt("distributorID");
			String status = rs.getString("status");
			String distributorName = rs.getString("distributorName");
			order.setOrderID(orderID);
			order.setProductID(productID);
			order.setProductName(productName);
			order.setQuantity(quantity);
			order.setDistributorID(distributorID);
			order.setStatus(status);
			order.setDistributorName(distributorName);

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
		return order;
	}

	public static void updateOrder(int id, int quantity, String status) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setString(2, status);
			preparedStatement.setInt(3, id);
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

	public static void newOrder(int productID, String productName, int quantity, int distributorID, String status,
			String distributorName) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_ORDER);
			preparedStatement.setInt(1, productID);
			preparedStatement.setString(2, productName);
			preparedStatement.setInt(3, quantity);
			preparedStatement.setInt(4, distributorID);
			preparedStatement.setString(5, status);
			preparedStatement.setString(6, distributorName);
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
	public static void deleteOrder(int orderID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
			preparedStatement.setInt(1, orderID);
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
