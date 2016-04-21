package com.db;


import java.sql.*;

public class DBConnection {
	
	private static String drivers = DBConfig.DRIVERS;
	private static String url = DBConfig.URL;
	private static String user = DBConfig.USER;
	private static String password = DBConfig.PASSWORD;
	public static Connection GetConnection() throws ClassNotFoundException
	{
		Connection conn = null;
		try { 
				Class.forName (drivers).newInstance();
		}catch (InstantiationException e)
		{
			e.printStackTrace();
		}catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try{
			conn = DriverManager.getConnection(url, user, password);
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return conn;				
	}
	
	public static void Close(Connection conn)
	{
		try{
			if (conn != null && !conn.isClosed())
				conn.close();
		}catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws ClassNotFoundException {
		Connection conn = GetConnection();
		try
		{
			Statement stmt = conn.createStatement(); // create Statement object
			ResultSet rset = stmt.executeQuery("SELECT * "
					                         + "FROM MOVIES "
					                         );
			
			while (rset.next())
			{
				System.out.println(rset.getString(1));
			}
			stmt.close(); // close Statement and release resources
			conn.close(); // close Connection and release resources		
		}
		catch (SQLException sqle) { 
			System.out.println("SQLException : " + sqle);// handle exceptions	
		}
	}
}
