package com.assign.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
	
	private static String URL = "jdbc:mysql://127.0.0.1/block_chain";
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String USER = "root";
	private static String PWD = "bobby";
			
	
	
	public static Connection getConnection() {

        try {
			//实例化驱动
			Class.forName(DRIVER);
			//获取连接
			Connection conn = DriverManager.getConnection(URL, USER, PWD);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Statement getStatement() {
		Statement statement = null;
		try {
			statement = JDBC.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return statement;
	}
	
	public static void close(Connection conn, PreparedStatement ps) {
		try {
            if (conn != null) conn.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
        try {
            if(conn!=null) conn.close();
            if(ps!=null) ps.close();
            if(rs!=null) rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
