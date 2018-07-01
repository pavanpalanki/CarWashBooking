package com.carwash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.carwash.business.*;
import com.carwash.util.ConnectionPool;
import com.carwash.util.DBUtil;

public class OwnerDao {

	public static ArrayList<Wash> getWashInfo(String email) {
		ArrayList<Wash> washes = new ArrayList<Wash>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_WASH_INFO;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				Wash wash = new Wash();
				wash.setWashId(rs.getInt(1));
				wash.setoEmail(rs.getString(2));
				wash.setWashType(rs.getString(3));
				wash.setWashPrice(rs.getFloat(4));
				washes.add(wash);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return washes;
	}

	public static void setPrices(String email, ArrayList<Float> prices) {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = SQLmanager.INSERT_WASH_PRICES;
		ArrayList<Integer> washIds = new ArrayList();
		washIds = getAllWashIds();
		try {
			for (int i = 0; i < washIds.size(); i++) {
				ps = connection.prepareStatement(query);
				ps.setString(1, email);
				ps.setInt(2, washIds.get(i));
				ps.setFloat(3, prices.get(i));
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	private static ArrayList<Integer> getAllWashIds() {
		// TODO Auto-generated method stub
		ArrayList<Integer> washIds = new ArrayList();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_WASH_IDS;
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				washIds.add(rs.getInt("wash_id"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return washIds;
	}
	
	public static void cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = SQLmanager.DELETE_BOOKING;
		try {
			ps = connection.prepareStatement(query);
			ps.setLong(1, Long.parseLong(bookingID));
			ps.executeUpdate();	

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static ArrayList<Order> getAllBookings(String oEmail) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		ArrayList<Order> bookings = new ArrayList<Order>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date date = new Date();
		String query = SQLmanager.GET_OWNER_BOOKINGS;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, oEmail);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order booking = new Order();
				booking.setId(rs.getString(1));
				booking.setCustomerEmail(rs.getString(2));
				booking.setDate(rs.getString(3));
				booking.setWashType(rs.getString(4));
				booking.setWashTime(rs.getString(5));
				if(rs.getInt(6)==1){
					booking.setCancel(true);
				}else{
					booking.setCancel(false);
				}
				bookings.add(booking);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return bookings;
	
	}
	public static ArrayList<Float> getPrices(String oEmail) {
		// TODO Auto-generated method stub
		ArrayList<Float> prices = new ArrayList<Float>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_PRICES;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, oEmail);
			rs = ps.executeQuery();
			while (rs.next()) {
				prices.add(rs.getFloat("price"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return prices;
	}
	public static void updatePrices(String email, ArrayList<Float> prices) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = SQLmanager.UPDATE_WASH_PRICES;
		ArrayList<Integer> washIds = new ArrayList();
		washIds = getAllWashIds();
		try {
			for (int i = 0; i < washIds.size(); i++) {
				ps = connection.prepareStatement(query);
				ps.setFloat(1, prices.get(i));
				ps.setString(2, email);
				ps.setInt(3, washIds.get(i));				
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	
	}
}
