package com.carwash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.mail.MessagingException;

import com.carwash.business.*;
import com.carwash.util.ConnectionPool;
import com.carwash.util.DBUtil;
import com.carwash.util.*;

public class CustomerDao {

	public static ArrayList<Wash> getAvailableSlots() { // TODO Auto-generated
														// method stub
		ArrayList<Wash> getAllWashes = new ArrayList<Wash>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getBusySlots = SQLmanager.GET_BUSY_SLOTS;
		String getOwners = SQLmanager.GET_ALL_OWNERS;
		try {
			ps = connection.prepareStatement(getOwners);
			rs = ps.executeQuery();
			while (rs.next()) {

				Wash getAvailableSlots = new Wash();
				getAvailableSlots.setoEmail(rs.getString(1));
				getAvailableSlots.setOwnerName(rs.getString(2));
				getAvailableSlots.setSlotId(rs.getInt(3));
				getAvailableSlots.setSlotTime(getSlotTimebyId(rs.getInt(3)));
				getAllWashes.add(getAvailableSlots);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return getAllWashes;
	}

	private static String getSlotTimebyId(int slotId) {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		String slotTime = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_SLOT_INFO_FROM_ID;
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, slotId);
			rs = ps.executeQuery();
			while (rs.next()) {
				slotTime = rs.getString("time");
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return slotTime;
	}

	// should return false if any booking is found..else true
	public static Boolean checkAvailability(String date, String time, String washType, String owner)
			throws ParseException {
		// TODO Auto-generated method stub
		Boolean check = true;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date convertedDate = sdf1.parse(date);
		java.sql.Date sqlDate = new java.sql.Date(convertedDate.getTime());
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.CHECK_AVAILABILITY;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, owner);
			ps.setString(2, washType);
			ps.setString(3, time);
			ps.setDate(4, sqlDate);
			rs = ps.executeQuery();
			while (rs.next()) {
				check = false;
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return check;
	}

	public static ArrayList getAllOwners() {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, String>> owners = new ArrayList<HashMap<String, String>>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_ALL_OWNERS_NAMES;
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				HashMap<String, String> owner = new HashMap<String, String>();
				owner.put(rs.getString("o_email"), rs.getString("car_wash_name"));
				owners.add(owner);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return owners;
	}

	public static ArrayList SetOrderParameters(String o_email, String c_email, String wash_id, String slot_id,
			String date, ArrayList displayParams) {
		// TODO Auto-generated method stub
		// write code to display all the order information customer entered and
		// confirm the order
		ArrayList params = new ArrayList();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = SQLmanager.DISPLAY_ORDER_INFO;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, o_email);
			ps.setString(2, slot_id);
			ps.setString(3, wash_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				// ow.car_wash_name,s.time,w.wash_type,p.price from owner
				// ow,slots s,washes w,price p
				params.add(rs.getString(1));
				params.add(rs.getString(3));
				params.add(date);
				params.add(rs.getString(2));
				params.add(rs.getString(4));
				displayParams.add(rs.getString(4));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return params;
	}

	public static ArrayList<Order> getAllBookings(String cEmail) {
		// TODO Auto-generated method stub
		ArrayList<Order> bookings = new ArrayList<Order>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String query = SQLmanager.GET_CUSTOMER_BOOKINGS;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, cEmail);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order booking = new Order();
				booking.setId(rs.getString(1));
				booking.setCarWash(rs.getString(2));
				booking.setDate(rs.getString(3));
				booking.setWashType(rs.getString(4));
				booking.setWashTime(rs.getString(5));
				if (rs.getInt(6) == 1) {
					booking.setCancel(true);
				} else {
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

	public static ArrayList<CarWashes> getAllCarWashInfo() {
		// TODO Auto-generated method stub
		ArrayList<CarWashes> cars=new ArrayList<CarWashes>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.GET_ALL_OWNERS_INFO;
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CarWashes car = new CarWashes();
				car.setCarWash(rs.getString("car_wash_name"));
				String address=rs.getLong("door_number")+", "+
						rs.getString("street")+", "+
						rs.getString("city")+", "+
						rs.getString("province")+", "+
						rs.getString("postal_code");
				car.setAddress(address);
				car.setEmail(rs.getString("o_email"));
				car.setPhone(rs.getString("phone"));
				cars.add(car);
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return cars;
	}

}
