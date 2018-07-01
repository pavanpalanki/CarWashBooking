package com.carwash.dao;

public class SQLmanager {

	static String INSERT_USER="insert into all_users values(?,?)";
	static String INSERT_USER_CUSTOMER="insert into customer(c_email,password,first_name,last_name,phone,door_number,street,city,province,postal_code) values(?,?,?,?,?,?,?,?,?,?)";
	static String INSERT_USER_OWNER="insert into owner(o_email,password,car_wash_name,phone,door_number,street,city,province,postal_code) values(?,?,?,?,?,?,?,?,?)";
	static String GET_USER="select * from all_users where email=?";
	static String INSERT_USER_PASSWORD="INSERT INTO userpassword (email, UserPassword, Salt) " + "VALUES (?, ?, ?)";
	static String GET_USER_PASSWORD="SELECT * FROM userpassword WHERE email = ?";
	static String GET_USER_ROLE="select role from all_users where email=?";
	static String GET_ALL_OWNERS="select email from all_users where role='owner'";
	static String GET_WASH_INFO="select w.wash_id,p.o_email,w.wash_type,p.price from price p, washes w  where p.wash_id=w.wash_id and p.o_email=?";
	static String INSERT_WASH_PRICES="insert into price(o_email,wash_id,price) values(?,?,?)";
	static String UPDATE_WASH_PRICES="update price set price=? where o_email=? and wash_id=?";
	static String GET_BUSY_SLOTS="select * from orders where o_email=? and  order_date>curdate()-1";
	static String ASSIGN_ALL_SLOTS="select o.o_email,s.slot_id,s.time from owner o join slots s";
	static String GET_WASH_IDS="select * from washes";
	static String GET_SLOT_INFO_FROM_ID="select * from slots where slot_id=?";
	static String CHECK_AVAILABILITY="select * from orders where o_email=? and wash_id=? and slot_id=? and order_date=?";
	static String GET_ALL_OWNERS_NAMES="select o_email,car_wash_name from owner";
	static String GET_ALL_OWNERS_INFO="select car_wash_name, door_number,street,city,province,postal_code,phone,o_email from owner";
	static String INSERT_ORDER_DETAILS="insert into orders(o_email,c_email,wash_id,slot_id,order_date,price) values(?,?,?,?,?,?)";
	public static final String DISPLAY_ORDER_INFO = "select ow.car_wash_name,s.time,w.wash_type,p.price from owner ow,slots s,washes w,price p "+
	" where ow.o_email=p.o_email and p.wash_id=w.wash_id and ow.o_email=? and s.slot_id=? and w.wash_id=?";
	public static final String GET_CUSTOMER_BOOKINGS="select o.order_id,ow.car_wash_name,o.order_date,w.wash_type,s.time,o.order_date>=NOW()-1 as cancel from orders o, owner ow, slots s, washes w  "+
	" where o.o_email=ow.o_email and o.slot_id=s.slot_id and o.wash_id=w.wash_id and o.c_email=?";
	public static final String DELETE_BOOKING="delete from orders where order_id=?";
	public static final String GET_BOOKING_DATA="select * from orders where order_id=?";
	public static final String GET_OWNER_BOOKINGS="select o.order_id,c.c_email,o.order_date,w.wash_type,s.time,o.order_date>=NOW()-1 as cancel from orders o, owner ow, slots s, washes w, customer c "+
			" where o.c_email=c.c_email and o.o_email=ow.o_email and o.slot_id=s.slot_id and o.wash_id=w.wash_id and o.o_email=?";
	public static final String GET_PRICES="select * from price where o_email=?";
}