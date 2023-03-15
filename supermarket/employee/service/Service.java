package supermarket.employee.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import supermarket.employee.model.Employee;
import supermarket.util.DbUtil;

public class Service {

	private static final String SELECT_EMPLOYEES = "SELECT * FROM employees";
	private static final String SELECT_EMPLOYEE_BY_NAME = "SELECT * FROM employees WHERE employeeName = ?";
	private static final String UPDATE_EMPLOYEE_DETAIL = "UPDATE employees SET email = ?, password = ?, phoneNumber = ?, DOB = ?, address = ?, employeeName = ?, role = ?, designation = ?, salary = ? WHERE employeeID = ?";
	private static final String NEW_EMPLOYEE = "INSERT INTO employees(email,password,phoneNumber,DOB,address,employeeName,role,designation,salary) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String DELETE_EMPLOYEE = "DELETE FROM employees WHERE employeeID = ?";
	private static final String GET_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeID = ?";

	public static ArrayList<Employee> getEmployees() {
		Connection connection = DbUtil.getConnection();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEES);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				int employeeID = rs.getInt("employeeID");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String phoneNumber = rs.getString("phoneNumber");
				java.sql.Date dob = rs.getDate("DOB");
				String address = rs.getString("address");
				String name = rs.getString("employeeName");
				String role = rs.getString("role");
				String designation = rs.getString("designation");
				BigDecimal salary = rs.getBigDecimal("salary");
				employee.setEmployeeID(employeeID);
				employee.setEmail(email);
				employee.setPassword(password);
				employee.setPhoneNumber(phoneNumber);
				employee.setDOB(dob);
				employee.setAddress(address);
				employee.setName(name);
				employee.setRole(role);
				employee.setDesignation(designation);
				employee.setSalary(salary);
				if((employee.getEmployeeID() != 8)) {
					employees.add(employee);
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
		return employees;
	}

	public static ArrayList<Employee> getEmployeeByName(String employeeName) {
		Connection connection = DbUtil.getConnection();
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMPLOYEE_BY_NAME);
			preparedStatement.setString(1, employeeName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Employee employee = new Employee();
				int employeeID = rs.getInt("employeeID");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String phoneNumber = rs.getString("phoneNumber");
				java.sql.Date dob = rs.getDate("DOB");
				String address = rs.getString("address");
				String name = rs.getString("employeeName");
				String role = rs.getString("role");
				String designation = rs.getString("designation");
				BigDecimal salary = rs.getBigDecimal("salary");
				employee.setEmployeeID(employeeID);
				employee.setEmail(email);
				employee.setPassword(password);
				employee.setPhoneNumber(phoneNumber);
				employee.setDOB(dob);
				employee.setAddress(address);
				employee.setName(name);
				employee.setRole(role);
				employee.setDesignation(designation);
				employee.setSalary(salary);
				employees.add(employee);
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
		return employees;
	}

	public static void updateEmployee(int employeeID, String email, String password, String phoneNumber,
			java.sql.Date DOB, String address, String employeeName, String role, String designation,
			BigDecimal salary) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_DETAIL);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setDate(4, DOB);
			preparedStatement.setString(5, address);
			preparedStatement.setString(6, employeeName);
			preparedStatement.setString(7, role);
			preparedStatement.setString(8, designation);
			preparedStatement.setBigDecimal(9, salary);
			preparedStatement.setInt(10, employeeID);
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

	public static void newEmployee(String email, String password, String phoneNumber, java.sql.Date DOB, String address,
			String employeeName, String role, String designation, BigDecimal salary) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_EMPLOYEE);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setDate(4, DOB);
			preparedStatement.setString(5, address);
			preparedStatement.setString(6, employeeName);
			preparedStatement.setString(7, role);
			preparedStatement.setString(8, designation);
			preparedStatement.setBigDecimal(9, salary);
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

	public static void deleteEmployee(int employeeID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE);
			preparedStatement.setInt(1, employeeID);
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

	public static Employee getEmployeeByID(int id) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				Employee employee = new Employee();
				int employeeID = rs.getInt("employeeID");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String phoneNumber = rs.getString("phoneNumber");
				java.sql.Date dob = rs.getDate("DOB");
				String address = rs.getString("address");
				String name = rs.getString("employeeName");
				String role = rs.getString("role");
				String designation = rs.getString("designation");
				BigDecimal salary = rs.getBigDecimal("salary");
				employee.setEmployeeID(employeeID);
				employee.setEmail(email);
				employee.setPassword(password);
				employee.setPhoneNumber(phoneNumber);
				employee.setDOB(dob);
				employee.setAddress(address);
				employee.setName(name);
				employee.setRole(role);
				employee.setDesignation(designation);
				employee.setSalary(salary);
				return employee;
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
		return null;
	}
}
