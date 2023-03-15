package supermarket.purchase.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import supermarket.order.model.Order;
import supermarket.purchase.model.Purchase;
import supermarket.sales.model.Invoice;
import supermarket.util.DbUtil;

public class Service {

	private static final String SELECT_RECIEVED_ORDERS = "SELECT * FROM orders WHERE status = 'received'";
	private static final String SELECT_RECIEVED_ORDERS_BY_ID = "SELECT * FROM orders WHERE orderID = ? AND status = 'received'";
	private static final String NEW_PURCHASE = "INSERT INTO purchases(distributorID,orderID,amount,dateAndTime,distributorName) VALUES(?,?,?,now(),?)";
	private static final String SELECT_PURCHASES = "SELECT * FROM purchases";
	private static final String SELECT_PURCHASE_BY_ID = "SELECT * FROM purchases WHERE orderID = ?";
	private static final String DELETE_PURCHASE = "DELETE FROM purchases WHERE purchaseID = ?";
	private static final String SEARCH_PURCHASES_BY_DATE = "SELECT * FROM purchases WHERE dateAndTime > ? AND dateAndTime < ?";
	private static final String GET_PURCHASES_BY_CATEGORY = "SELECT products.category,SUM(purchases.amount) AS total FROM products,purchases,orders WHERE purchases.orderID = orders.orderID AND products.productID = orders.productID AND purchases.dateAndTime > ? AND purchases.dateAndTime < ? GROUP BY products.category";
	private static final String TOTAL_PURCHASES_BY_DATE = "SELECT SUM(amount) AS total FROM purchases WHERE dateAndTime > ? AND dateAndTime < ?";
	
	public static ArrayList<Order> getRecievdOrders() {
		Connection connection = DbUtil.getConnection();
		ArrayList<Order> orders = new ArrayList<Order>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECIEVED_ORDERS);
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

	public static Order getRecievedOrderByID(int id) {
		Connection connection = DbUtil.getConnection();
		Order order = new Order();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_RECIEVED_ORDERS_BY_ID);
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

	public static void newPurchase(int distributorID, int orderID, BigDecimal amount, String distributorName) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_PURCHASE);
			preparedStatement.setInt(1, distributorID);
			preparedStatement.setInt(2, orderID);
			preparedStatement.setBigDecimal(3, amount);
			preparedStatement.setString(4, distributorName);
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

	public static ArrayList<Purchase> getPurchases() {
		Connection connection = DbUtil.getConnection();
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASES);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Purchase purchase = new Purchase();
				int purchaseID = rs.getInt("purchaseID");
				int distributorID = rs.getInt("distributorID");
				int orderID = rs.getInt("orderID");
				BigDecimal amount = rs.getBigDecimal("amount");
				java.sql.Timestamp dateTime = rs.getTimestamp("dateAndTime");
				String distributorName = rs.getString("distributorName");
				purchase.setPurchaseID(purchaseID);
				purchase.setDistributorID(distributorID);
				purchase.setOrderID(orderID);
				purchase.setAmount(amount);
				purchase.setDateTime(dateTime);
				purchase.setDistributorName(distributorName);
				purchases.add(purchase);
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
		return purchases;
	}

	public static Purchase getPurchaseByID(int id) {
		Connection connection = DbUtil.getConnection();
		Purchase purchase = new Purchase();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PURCHASE_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int purchaseID = rs.getInt("purchaseID");
			int distributorID = rs.getInt("distributorID");
			int orderID = rs.getInt("orderID");
			BigDecimal amount = rs.getBigDecimal("amount");
			java.sql.Timestamp dateTime = rs.getTimestamp("dateAndTime");
			String distributorName = rs.getString("distributorName");
			purchase.setPurchaseID(purchaseID);
			purchase.setDistributorID(distributorID);
			purchase.setOrderID(orderID);
			purchase.setAmount(amount);
			purchase.setDateTime(dateTime);
			purchase.setDistributorName(distributorName);

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
		return purchase;
	}
	public static void deletePurchase(int purchaseID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PURCHASE);
			preparedStatement.setInt(1, purchaseID);
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
	public static JSONObject purchasesChart() {
		Connection connection = DbUtil.getConnection();
		JSONObject json = new JSONObject();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(GET_PURCHASES_BY_CATEGORY);
			String date = java.time.LocalDate.now().toString();
			java.sql.Timestamp dayStart = java.sql.Timestamp.valueOf(date + " 00:00:00");
			java.sql.Timestamp dayEnd = java.sql.Timestamp.valueOf(date + " 23:59:59");
			preparedStatement.setTimestamp(1, dayStart);
			preparedStatement.setTimestamp(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String category = rs.getString("category");
				BigDecimal total = rs.getBigDecimal("total");
				try {
					json.put(category, total);
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

	public static JSONObject purchasesChartByDate(String date) {
		Connection connection = DbUtil.getConnection();
		JSONObject json = new JSONObject();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(GET_PURCHASES_BY_CATEGORY);
			java.sql.Timestamp dayStart = java.sql.Timestamp.valueOf(date + " 00:00:00");
			java.sql.Timestamp dayEnd = java.sql.Timestamp.valueOf(date + " 23:59:59");
			preparedStatement.setTimestamp(1, dayStart);
			preparedStatement.setTimestamp(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String category = rs.getString("category");
				BigDecimal total = rs.getBigDecimal("total");
				try {
					json.put(category, total);
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
	public static ArrayList<Purchase> searchPurchasesByDate(String date) {
		Connection connection = DbUtil.getConnection();
		ArrayList<Purchase> purchases = new ArrayList<Purchase>();
		java.sql.Timestamp dayStart = java.sql.Timestamp.valueOf(date + " 00:00:00");
		java.sql.Timestamp dayEnd = java.sql.Timestamp.valueOf(date + " 23:59:59");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PURCHASES_BY_DATE);
			preparedStatement.setTimestamp(1, dayStart);
			preparedStatement.setTimestamp(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Purchase purchase = new Purchase();
				int purchaseID = rs.getInt("purchaseID");
				int distributorID = rs.getInt("distributorID");
				int orderID = rs.getInt("orderID");
				BigDecimal amount = rs.getBigDecimal("amount");
				java.sql.Timestamp dateTime = rs.getTimestamp("dateAndTime");
				String distributorName = rs.getString("distributorName");
				purchase.setPurchaseID(purchaseID);
				purchase.setDistributorID(distributorID);
				purchase.setOrderID(orderID);
				purchase.setAmount(amount);
				purchase.setDateTime(dateTime);
				purchase.setDistributorName(distributorName);
				purchases.add(purchase);
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
		return purchases;
	}
	public static BigDecimal totalPurchases() {
		Connection connection = DbUtil.getConnection();
		BigDecimal total = new BigDecimal(0);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_PURCHASES_BY_DATE);
			String date = java.time.LocalDate.now().toString();
			java.sql.Timestamp dayStart = java.sql.Timestamp.valueOf(date + " 00:00:00");
			java.sql.Timestamp dayEnd = java.sql.Timestamp.valueOf(date + " 23:59:59");
			preparedStatement.setTimestamp(1, dayStart);
			preparedStatement.setTimestamp(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			total = rs.getBigDecimal("total");
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

	public static BigDecimal totalPurchasesByDate(String date) {
		Connection connection = DbUtil.getConnection();
		BigDecimal total = new BigDecimal(0);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_PURCHASES_BY_DATE);
			java.sql.Timestamp dayStart = java.sql.Timestamp.valueOf(date + " 00:00:00");
			java.sql.Timestamp dayEnd = java.sql.Timestamp.valueOf(date + " 23:59:59");
			preparedStatement.setTimestamp(1, dayStart);
			preparedStatement.setTimestamp(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			total = rs.getBigDecimal("total");
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
	public static JSONObject totalPurchasesLastSevenDays() {
		JSONObject json = new JSONObject();
		for(int i = 7;i >=0; i--) {
			String date = java.time.LocalDate.now().minusDays(i).toString();
			BigDecimal total = totalPurchasesByDate(date);
			try {
				json.put(date, total);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}
	public static BigDecimal sumOfTotalPurchasesLastSevenDays() {
		BigDecimal totalSum = new BigDecimal(0);
		for(int i = 7;i >=0; i--) {
			String date = java.time.LocalDate.now().minusDays(i).toString();
			BigDecimal total = totalPurchasesByDate(date);
			if(total != null) {
				totalSum = totalSum.add(total);
			}
		}
		return totalSum;
	}

}
