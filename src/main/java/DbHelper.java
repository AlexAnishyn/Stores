package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbHelper {
	
	//  JDBC URL, username and password of MySQL server
	
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "password";	
	
    /**
     *  Create method for connect to the database 
     */
	static void connect(){
		try{
			con = DriverManager.getConnection(url, user, password);
    		stmt = con.createStatement();
		}catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	/**
     *  Create method to close the connection to the database 
     */
	static void close(){
		try { 
			con.close(); 
			stmt.close();
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}
	
	/**
     *  Create method to INSERT-query in JDBC 
     */
	public static void executeQuery(String query){
		try{
			stmt.executeUpdate(query);
		}catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	/**
     *  Create method to retrieve the data using SELECT-query in JDBC 
     */
	public static void resultSet (String query){
		try{
			rs = stmt.executeQuery(query);
		}catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	/**
	 *  Create method which saved result query (method 'resultSet')
	 *  in variable 'limit'
	 */
	public static int getLimit(){
		int limit = 0;	
		
		try{
			 while (rs.next()){
				 limit = rs.getInt(1);
			 }
		}catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return limit;
	}
	
	//  JDBC variables for opening and managing connection
	
	private static ResultSet rs;
	private static Connection con;
    private static Statement stmt;
}