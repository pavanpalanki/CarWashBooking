package com.carwash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.carwash.business.Wash;
import com.carwash.util.ConnectionPool;
import com.carwash.util.DBUtil;

public class SamplesTest {

	public static void main(String[] args) {

		// TODO Auto-generated method stub
		ArrayList<Wash> getAllWashes = new ArrayList<Wash>();
		ArrayList<String> owners = new ArrayList<String>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String getOwners = SQLmanager.GET_ALL_OWNERS;
		String getBusySlots = SQLmanager.GET_BUSY_SLOTS;
		try {
			ps = connection.prepareStatement(getOwners);
			rs = ps.executeQuery();
			while (rs.next()) {
				owners.add(rs.getString("o_email"));

				/*
				 * * Wash getAvailableSlots = new Wash();
				 * getAvailableSlots.setoEmail(rs.getString(1));
				 * getAvailableSlots.setOwnerName(rs.getString(2));
				 * getAvailableSlots.setSlotId(rs.getInt(3));
				 * //getAvailableSlots.setSlotTime();
				 * getAllWashes.add(getAvailableSlots);
				 */
			}
			for (String owner : owners) {
				ps = connection.prepareStatement(getBusySlots);
				ps.setString(1, owner);
				rs = ps.executeQuery();
				here: while (rs.next()) {
					break here;
				}
				
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

}
