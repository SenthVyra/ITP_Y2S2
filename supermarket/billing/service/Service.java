package supermarket.billing.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import supermarket.billing.model.InvoiceItem;
import supermarket.util.DbUtil;

public class Service {

	private static final String GET_PRODUCT = "SELECT * FROM products WHERE productID = ?";
	private static final String NEW_INVOICE = "INSERT INTO invoices(dateAndTime,total) VALUES(now(),?)";
	private static final String INSERT_INVOICE_DETAILS = "INSERT INTO invoiceDetails(invoiceID,price,productID,productName,quantity) VALUES(?,?,?,?,?)";
	private static final String GET_LAST_INVOICE_DETAILS = "SELECT * FROM invoiceDetails WHERE invoiceID = (SELECT invoiceID FROM invoices ORDER BY invoiceID DESC LIMIT 1)";
    private static final String DELETE_LAST_INVOICE = "DELETE FROM invoices ORDER BY invoiceID DESC LIMIT 1";
	private static final String UPDATE_STOCK = "UPDATE stocks SET quantity = quantity + ? WHERE productID = ?";
	private static final String GET_DISCOUNT = "SELECT discount FROM discounts WHERE productID = ? AND quantity = ?";
    
	public static JSONObject getProduct(int productID,int quantity) {
		Connection connection = DbUtil.getConnection();
		JSONObject json = new JSONObject();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_PRODUCT);
			preparedStatement.setInt(1, productID);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				String name = result.getString("productName");
				BigDecimal price = result.getBigDecimal("price");
				try {
					json.put("name", name);
					json.put("price", price.toString());
					json.put("discount", getDiscount(productID,quantity));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
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

	private static int createInvoice(BigDecimal total) {
		Connection connection = DbUtil.getConnection();
		int invoiceID = -1;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_INVOICE,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setBigDecimal(1, total);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			invoiceID = rs.getInt(1);
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
		return invoiceID;
	}

	public static void setInvoiceDetails(BigDecimal total, String items) {
		Connection connection = DbUtil.getConnection();
		int invoiceID = createInvoice(total);
		try {
			JSONArray arr = new JSONArray(items);
			for (int i = 0; i < arr.length(); i++) {
				JSONObject obj = arr.getJSONObject(i);
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INVOICE_DETAILS);
					preparedStatement.setInt(1, invoiceID);
					preparedStatement.setBigDecimal(2, new BigDecimal(obj.getInt("price")));
					preparedStatement.setInt(3, obj.getInt("productID"));
					preparedStatement.setString(4, obj.getString("name"));
					preparedStatement.setInt(5, obj.getInt("quantity"));
					preparedStatement.executeUpdate();
					updateStock(obj.getInt("productID"),-obj.getInt("quantity"));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
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

	public static ArrayList<InvoiceItem> getLastInvoice() {
		Connection connection = DbUtil.getConnection();
		ArrayList<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_INVOICE_DETAILS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				InvoiceItem invoiceItem = new InvoiceItem();
				String name = rs.getString("productName");
				int quantity = rs.getInt("quantity");
				BigDecimal price = rs.getBigDecimal("price");
				int productID = rs.getInt("productID");
				invoiceItem.setProductID(productID);
				invoiceItem.setName(name);
				invoiceItem.setQuantity(quantity);
				invoiceItem.setPrice(price);
				invoiceItems.add(invoiceItem);
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
		return invoiceItems;
	}
	public static void deleteLastInvoice() {
		Connection connection = DbUtil.getConnection();
		try {
			ArrayList<InvoiceItem> items = getLastInvoice();
			for(InvoiceItem i:items) {
				updateStock(i.getProductID(),i.getQuantity());
			}
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LAST_INVOICE);
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
	public static void updateStock(int productID,int quantity) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STOCK);
			preparedStatement.setInt(1, quantity);
			preparedStatement.setInt(2, productID);
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
	public static int getDiscount(int productID,int quantity) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_DISCOUNT);
			preparedStatement.setInt(1, productID);
			preparedStatement.setInt(2, quantity);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt("discount");
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
		return 0;
	}
}
