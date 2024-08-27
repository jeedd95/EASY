package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {
	   /**
	    * 로드
	    * */
		static {
			try {
			  Class.forName(DbProperties.DRIVER_NAME);
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 연결
		 * */
		public static Connection getConnection() throws SQLException{
			 Connection con = DriverManager.getConnection(DbProperties.URL, 
					 DbProperties.USER_ID, 
					 DbProperties.USER_PASS);
			 
			 return con;
		}
				
		
		/**
		 * 닫기(DDL or DML인경우 ) 
		 * */
		 public static void dbClose(Connection con, Statement st) {
			 try {
				 if(con!=null) con.close();
				 dbClose(st);
			 }catch (SQLException e) {
				 e.printStackTrace();
			 }
		 }
		
		 public static void dbClose(Statement st) {
			 try {
				 if(st != null) st.close();
			 }catch (SQLException e) {
				 e.printStackTrace();
			 }
		 }
		
		 
		/**
		 * 닫기(select인 경우 ) 
		 * */
	     public static void dbClose(Connection con, Statement st , ResultSet rs) {
	    	 try {
			 if(rs!=null)rs.close();
			   dbClose(con, st);
	    	 }catch (SQLException e) {
				e.printStackTrace();
			}
		 }
	     public static void dbClose(Statement st , ResultSet rs) {
	    	 try {
				 if(rs!=null)rs.close();
		    	 	dbClose(st);
		    	 }catch (SQLException e) {
					e.printStackTrace();
				}
		 }

	}