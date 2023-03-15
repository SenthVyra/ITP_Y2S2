package supermarket.payment.service;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import supermarket.payment.model.Payment;
import supermarket.util.DbUtil;

public class Service {
	
	private static final String SELECT_PAYMENTS = "SELECT * FROM pays";
	private static final String SELECT_PAYMENT_BY_EMPLOYEE_NAME = "SELECT * FROM pays WHERE name = ?";
	private static final String UPDATE_PAYMENT = "UPDATE pays SET paid = ? WHERE empID = ?";
	private static final String NEW_PAYMENT = "INSERT INTO pays(empID,name,paid,pay_date) VALUES(?,?,?,?)";
	
	public static ArrayList<Payment> getPayments() {
		Connection connection = DbUtil.getConnection();
		ArrayList<Payment> payments = new ArrayList<Payment>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENTS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Payment payment = new Payment();
				int employeeID = rs.getInt("empID");
				java.sql.Date date = rs.getDate("pay_date");
				String name = rs.getString("name");
				BigDecimal paid = rs.getBigDecimal("paid");
				payment.setEmpID(employeeID);
				payment.setName(name);
				payment.setDate(date);
				payment.setPaid(paid);
				payments.add(payment);
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
		return payments;
	}

	public static ArrayList<Payment> getPaymentByEmployeeName(String nme) {
		Connection connection = DbUtil.getConnection();
		ArrayList<Payment> payments = new ArrayList<Payment>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PAYMENT_BY_EMPLOYEE_NAME);
			preparedStatement.setString(1, nme);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Payment payment = new Payment();
				int employeeID = rs.getInt("empID");
				java.sql.Date date = rs.getDate("pay_date");
				String name = rs.getString("name");
				BigDecimal paid = rs.getBigDecimal("paid");
				payment.setEmpID(employeeID);
				payment.setName(name);
				payment.setDate(date);
				payment.setPaid(paid);
				payments.add(payment);
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
		return payments;
	}

	public static void updatePayment(int employeeID,BigDecimal salary) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PAYMENT);
			preparedStatement.setBigDecimal(1, salary);
			preparedStatement.setInt(2, employeeID);
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

	public static void newPayment(int empID,BigDecimal salary,String name) {
		Connection connection = DbUtil.getConnection();
		java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_PAYMENT);
			preparedStatement.setInt(1, empID);
			preparedStatement.setString(2, name);
			preparedStatement.setBigDecimal(3, salary);
			preparedStatement.setDate(4, sqlDate);
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
