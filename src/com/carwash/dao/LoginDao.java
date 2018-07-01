package com.carwash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.carwash.business.Customer;
import com.carwash.business.Password;
import com.carwash.util.ConnectionPool;
import com.carwash.util.DBUtil;

public class LoginDao {

	public static Integer insertUser(Customer cust) {
		// TODO Auto-generated method stub
		int i = 0;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		if (cust.getLastName() == null) {
			cust.setLastName("");
		}
		Boolean checkEmailisUnique = emailUniqueness(cust.getEmail());
		int success = 0;

		try {
			if (checkEmailisUnique) {

				if (cust.getRole().equals("customer")) {
					String query = SQLmanager.INSERT_USER_CUSTOMER;
					ps = connection.prepareStatement(query);
					ps.setString(1, cust.getEmail());
					ps.setString(2, cust.getPassword());
					ps.setString(3, cust.getFirstName());
					ps.setString(4, cust.getLastName());
					ps.setString(5, cust.getPhoneNumber());
					ps.setInt(6, cust.getDoorNo());
					ps.setString(7, cust.getStreet());
					ps.setString(8, cust.getCity());
					ps.setString(9, cust.getProvince());
					ps.setString(10, cust.getPostalCode());
					success = ps.executeUpdate();
				} else if (cust.getRole().equals("owner")) {
					String query = SQLmanager.INSERT_USER_OWNER;
					ps = connection.prepareStatement(query);
					ps.setString(1, cust.getEmail());
					ps.setString(2, cust.getPassword());
					ps.setString(3, cust.getFirstName() + " " + cust.getLastName());
					ps.setString(4, cust.getPhoneNumber());
					ps.setInt(5, cust.getDoorNo());
					ps.setString(6, cust.getStreet());
					ps.setString(7, cust.getCity());
					ps.setString(8, cust.getProvince());
					ps.setString(9, cust.getPostalCode());
					success = ps.executeUpdate();
				}
				ps = connection.prepareStatement(SQLmanager.INSERT_USER);
				ps.setString(1, cust.getEmail());
				ps.setString(2, cust.getRole());
				ps.executeUpdate();
			}

			/*
			 * if (cust.getRole().equals("owner")) {
			 * OwnerDao.setPrices(cust.getEmail()); }
			 */

			return success;
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}

	}

	private static Boolean emailUniqueness(String email) {
		// TODO Auto-generated method stub
		Boolean unique = true;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_USER;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				unique = false;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return unique;
	}

	public static int insertUSerPassword(String email, String passwordhash, String salt) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = SQLmanager.INSERT_USER_PASSWORD;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, passwordhash);
			ps.setString(3, salt);

			return ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static Password getPassword(String email) {
		// TODO Auto-generated method stub

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Password password = null;
		String query = SQLmanager.GET_USER_PASSWORD;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			password = new Password();
			if (rs.next()) {
				password.setEmail(rs.getString("email"));
				password.setUserPassword(rs.getString("UserPassword"));
				password.setSalt(rs.getString("Salt"));

			}
			return password;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}

	}

	public static boolean validateUser(String password, String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean value = false;
		String query = SQLmanager.GET_USER_PASSWORD;
		String hashedPassword = null;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				hashedPassword = rs.getString("UserPassword");
			}
			if (password.equals(hashedPassword))
				value = true;
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return value;
	}

	public static String checkRole(String email) {
		// TODO Auto-generated method stub
		String role = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_USER_ROLE;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				role = rs.getString(1);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return role;
	}
}
