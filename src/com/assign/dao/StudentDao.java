package com.assign.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.assign.entites.Student;
import com.assign.jdbc.JDBC;

public class StudentDao {
	
	/**
	 * Get a Student Object by a nickName.
	 * @param nickName
	 * @return
	 */
	public Student findStudent(String nickName) {
		Student s = new Student();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JDBC.getStatement();
			String sql = "SELECT studentID, fullName, nickName, password FROM Student where nickName = '" + nickName + "'";
			//System.out.println(sql);
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				s.setStudentID(rs.getInt("studentID"));
				s.setFullName(rs.getString("fullName"));
				s.setNickName(rs.getString("nickName"));
				s.setPassword(rs.getString("password"));
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
		
		return s;
	}
	
	/**
	 * Get the latestVersion by studentID
	 * @param studentID
	 * @return
	 */
	public int getLatestVersion(int studentID) {
		
		int latestVersion = 0;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JDBC.getStatement();
			String sql = "SELECT latestVersion FROM student where studentID = " + studentID;
			
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				latestVersion = rs.getInt("latestVersion");
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
		return latestVersion;
	}
	
	public void updateLatestVersion(int studentID, int latestVersion) {
		
		String sql = "update Student set latestVersion = ? where studentID = ?";
		PreparedStatement prepStatement = null;
		Connection connection = null;
		try {
			connection = JDBC.getConnection();
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, latestVersion);
			prepStatement.setInt(2, studentID);
			prepStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (prepStatement != null) {
					prepStatement.close();
				}

				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	}

}
