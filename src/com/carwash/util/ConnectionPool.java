package com.carwash.util;


import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ConnectionPool {

    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection(
                    //"jdbc:mysql://localhost:3306/carwash?user=root&password=root");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sl-us-south-1-portal.33.dblayer.com:57704/carwash?user=admin&password=OSNTKJEPHVIPLGWH");
            /*InitialContext ic = new InitialContext();
            ic.bind("java:/comp/env/jdbc/carwash", con);*/
            /*final String DB_URL = "jdbc:mysql://carwashdb.czcorxm8kkgd.us-west-2.rds.amazonaws.com:3306/carwash";
    		final String USER = "admin";
    		final String PASS = "akshaykumar";//MAWMYDRWSLPMUVQA
    		Connection con = DriverManager.getConnection(DB_URL, USER, PASS);*/
            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }    
    
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();            
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/carwash");
        } catch (NamingException e) {
            System.out.println(e);
        }
    } 

    public static synchronized ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

   // public Connection getConnection() {
     //   try {
    //        return dataSource.getConnection();
   //     } catch (SQLException e) {
   //         System.out.println(e);
   //         return null;
  //      }
//    }

    public void freeConnection(Connection c) {
        try {if(c!= null){
            c.close();
        c = null;
        }} catch (SQLException e) {
            System.out.println(e);
        }
    }
}
