package com.assign.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.assign.entites.Lecturer;
import com.assign.jdbc.JDBC;

public class LecturerDao {
	
	public Lecturer findLecturer(String nickName) {
		Lecturer lec = new Lecturer();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JDBC.getStatement();
			String sql = "SELECT lecturerID, fullName, nickName, password FROM Lecturer where nickName = '" + nickName + "'";
			//System.out.println(sql);
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				lec.setLecturerID(rs.getInt("lecturerID"));
				lec.setFullName(rs.getString("fullName"));
				lec.setNickName(rs.getString("nickName"));
				lec.setPassword(rs.getString("password"));
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {

					statement.close();

					rs = null;
				}

				if (connection != null) {
					connection.close();
					connection = null;
				}
				
				if (rs != null) {
					rs.close();
					rs = null;
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return lec;
	}

}
