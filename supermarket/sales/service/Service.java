package supermarket.sales.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import supermarket.sales.model.Invoice;
import supermarket.util.DbUtil;

public class Service {

	private static final String SELECT_INVOICES = "SELECT * FROM invoices";
	private static final String SEARCH_INVOICES_BY_ID = "SELECT * FROM invoices WHERE invoiceID = ?";
	private static final String SEARCH_INVOICES_BY_DATE = "SELECT * FROM invoices WHERE dateAndTime > ? AND dateAndTime < ?";
	private static final String SALES_BY_CATEGORY = "SELECT products.category, SUM(invoiceDetails.price) AS total FROM products,invoiceDetails,invoices WHERE invoiceDetails.productID = products.productID AND invoices.invoiceID = invoiceDetails.invoiceID AND invoices.dateAndTime > ? AND invoices.dateAndTime < ? GROUP BY products.category";
	private static final String TOTAL_SALES_BY_DATE = "SELECT SUM(total) AS total FROM invoices WHERE dateAndTime > ? AND dateAndTime < ?";
    private static final String DELETE_INVOICE = "DELETE FROM invoices WHERE invoiceID = ?";
	
	public static ArrayList<Invoice> getInvoices() {
		Connection connection = DbUtil.getConnection();
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INVOICES);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Invoice invoice = new Invoice();
				int invoiceID = rs.getInt("invoiceID");
				BigDecimal total = rs.getBigDecimal("total");
				java.sql.Timestamp dateTime = rs.getTimestamp("dateAndTime");
				invoice.setDateTime(dateTime);
				invoice.setInvoiceId(invoiceID);
				invoice.setTotal(total);
				invoices.add(invoice);
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
		return invoices;
	}

	public static Invoice searchInvoicesByID(int id) {
		Connection connection = DbUtil.getConnection();
		Invoice invoice = new Invoice();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_INVOICES_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int invoiceID = rs.getInt("invoiceID");
			BigDecimal total = rs.getBigDecimal("total");
			java.sql.Timestamp dateTime = rs.getTimestamp("dateAndTime");
			invoice.setDateTime(dateTime);
			invoice.setInvoiceId(invoiceID);
			invoice.setTotal(total);

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
		return invoice;
	}

	public static ArrayList<Invoice> searchInvoicesByDate(String date) {
		Connection connection = DbUtil.getConnection();
		ArrayList<Invoice> invoices = new ArrayList<Invoice>();
		java.sql.Timestamp dayStart = java.sql.Timestamp.valueOf(date + " 00:00:00");
		java.sql.Timestamp dayEnd = java.sql.Timestamp.valueOf(date + " 23:59:59");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_INVOICES_BY_DATE);
			preparedStatement.setTimestamp(1, dayStart);
			preparedStatement.setTimestamp(2, dayEnd);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Invoice invoice = new Invoice();
				int invoiceID = rs.getInt("invoiceID");
				BigDecimal total = rs.getBigDecimal("total");
				java.sql.Timestamp dateTime = rs.getTimestamp("dateAndTime");
				invoice.setDateTime(dateTime);
				invoice.setInvoiceId(invoiceID);
				invoice.setTotal(total);
				invoices.add(invoice);
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
		return invoices;
	}

	public static JSONObject salesChart() {
		Connection connection = DbUtil.getConnection();
		JSONObject json = new JSONObject();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SALES_BY_CATEGORY);
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

	public static JSONObject salesChartByDate(String date) {
		Connection connection = DbUtil.getConnection();
		JSONObject json = new JSONObject();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(SALES_BY_CATEGORY);
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

	public static BigDecimal totalSales() {
		Connection connection = DbUtil.getConnection();
		BigDecimal total = new BigDecimal(0);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_SALES_BY_DATE);
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

	public static BigDecimal totalSalesByDate(String date) {
		Connection connection = DbUtil.getConnection();
		BigDecimal total = new BigDecimal(0);
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_SALES_BY_DATE);
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
	public static JSONObject totalSalesLastSevenDays() {
		JSONObject json = new JSONObject();
		for(int i = 7;i >=0; i--) {
			String date = java.time.LocalDate.now().minusDays(i).toString();
			BigDecimal total = totalSalesByDate(date);
			try {
				json.put(date, total);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}
	public static BigDecimal sumOfTotalSalesLastSevenDays() {
		BigDecimal totalSum = new BigDecimal(0);
		for(int i = 7;i >=0; i--) {
			String date = java.time.LocalDate.now().minusDays(i).toString();
			BigDecimal total = totalSalesByDate(date);
			if(total != null) {
				totalSum = totalSum.add(total);
			}
		}
		return totalSum;
	}
	public static void deleteInvoice(int invoiceID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INVOICE);
			preparedStatement.setInt(1, invoiceID);
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
