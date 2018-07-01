package com.carwash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.mail.MessagingException;

import com.carwash.util.ConnectionPool;
import com.carwash.util.DBUtil;
import com.carwash.util.MailUtilGmail;

public class OrderDao {

	public static void insertOrder(ArrayList<String> actialParams) {
		// TODO Auto-generated method stub
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQLmanager.INSERT_ORDER_DETAILS;
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date convertedDate = null;
		try {
			convertedDate = sdf1.parse(actialParams.get(4));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		java.sql.Date sqlStartDate = new java.sql.Date(convertedDate.getTime());
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, actialParams.get(0));
			ps.setString(2, actialParams.get(1));
			ps.setInt(3, Integer.parseInt(actialParams.get(2)));
			ps.setInt(4, Integer.parseInt(actialParams.get(3)));
			ps.setDate(5, sqlStartDate);
			ps.setFloat(6, Float.parseFloat(actialParams.get(5)));
			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	public static void cancelBooking(String bookingID) {
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

	public static void emailCancellation(String bookingID) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String to = null;
		String from = "carwashwindsor@gmail.com";
		String subject = "Cancelled order " + bookingID;
		String body = "";
		boolean isBodyHTML = true;
		String query = SQLmanager.GET_BOOKING_DATA;
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, bookingID);
			rs = ps.executeQuery();
			if (rs.next()) {
				try {
					MailUtilGmail.sendMail(rs.getString("o_email"), from, subject, body, isBodyHTML);
					MailUtilGmail.sendMail(rs.getString("c_email"), from, subject, body, isBodyHTML);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

}
