package supermarket.distributor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import supermarket.distributor.model.Distributor;
import supermarket.distributor.model.DistributorWithOrderCount;
import supermarket.util.DbUtil;

public class Service {
	
	private static final String SELECT_DISTRIBUTORS = "SELECT * FROM distributors";
	private static final String SELECT_DISTRIBUTOR_BY_NAME = "SELECT * FROM distributors WHERE distributorName = ?";
	private static final String UPDATE_DISTRIBUTOR = "UPDATE distributors SET distributorName = ?, address = ?, phoneNumber = ?, email = ? WHERE distributorID = ?";
	private static final String NEW_DISTRIBUTOR = "INSERT INTO distributors(distributorName,address,phoneNumber,email) VALUES(?,?,?,?)";
	private static final String DELETE_DISTRIBUTOR = "DELETE FROM distributors WHERE distributorID = ?";
	private static final String SELECT_DISTRIBUTOR_WITH_ORDER_COUNT = "SELECT distributorName,COUNT(orderID) AS orderCount FROM orders GROUP BY distributorName ORDER BY COUNT(orderID) DESC LIMIT 4";
	
	public static ArrayList<DistributorWithOrderCount> getDistributorWithOrderCount(){
		ArrayList<DistributorWithOrderCount> list = new ArrayList<>(4);
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTRIBUTOR_WITH_ORDER_COUNT);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				DistributorWithOrderCount dist = new DistributorWithOrderCount();
				String name = rs.getString("distributorName");
				int orderCount = rs.getInt("orderCount");
				dist.setName(name);
				dist.setOrderCount(orderCount);
				list.add(dist);
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
		return list;
	}
	public static ArrayList<Distributor> getDistributors(){
		Connection connection = DbUtil.getConnection();
		ArrayList<Distributor> distributors = new ArrayList<Distributor>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTRIBUTORS);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Distributor distributor = new Distributor();
				String name = rs.getString("distributorName");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				int distributorID = rs.getInt("distributorID");
				distributor.setDistributorName(name);
				distributor.setAddress(address);
				distributor.setPhoneNumber(phoneNumber);
				distributor.setEmail(email);
				distributor.setDistributorID(distributorID);
				distributors.add(distributor);
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
		return distributors;
	}
	public static ArrayList<Distributor> getDistributorByName(String distributorName){
		Connection connection = DbUtil.getConnection();
		ArrayList<Distributor> distributors = new ArrayList<Distributor>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DISTRIBUTOR_BY_NAME);
			preparedStatement.setString(1, distributorName);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Distributor distributor = new Distributor();
				String name = rs.getString("distributorName");
				String address = rs.getString("address");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
				int distributorID = rs.getInt("distributorID");
				distributor.setDistributorName(name);
				distributor.setAddress(address);
				distributor.setPhoneNumber(phoneNumber);
				distributor.setEmail(email);
				distributor.setDistributorID(distributorID);
				distributors.add(distributor);
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
		return distributors;
	}
	public static void updateDistributor(String name,String address,String phoneNumber,String email,int distributorID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISTRIBUTOR);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setString(4, email);
			preparedStatement.setInt(5, distributorID);
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
	public static void newDistributor(String name,String address,String phoneNumber,String email) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(NEW_DISTRIBUTOR);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setString(4, email);
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
	public static void deleteDistributor(int distributorID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DISTRIBUTOR);
			preparedStatement.setInt(1, distributorID);
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
