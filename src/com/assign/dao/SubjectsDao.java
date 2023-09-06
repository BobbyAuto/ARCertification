package com.assign.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.assign.entites.Subject;
import com.assign.jdbc.JDBC;

public class SubjectsDao {
	
	/**
	 * Get all the subjects which the lecturer is taking by lecturerID.
	 * @param lecturerID
	 * @return An arraylist with subject elements.
	 */
	public ArrayList<Subject> getLecturerSubjects(Integer lecturerID) {
		ArrayList<Subject> subjectsArray = new ArrayList<Subject>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JDBC.getStatement();
			String sql = "select sl.lecturerID, sl.subjectID, su.subjectName, su.subjectCode "
					+ "from SubjectAndLecturer as sl left join Subject as su "
					+ "on sl.subjectID = su.subjectID "
					+ "where sl.lecturerID = " + lecturerID;
			//System.out.println(sql);
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Subject sub = new Subject();
				sub.setSubjectID(rs.getInt("subjectID"));
				sub.setSubjectName(rs.getString("subjectName"));
				sub.setSubjectCode(rs.getString("subjectCode"));
				subjectsArray.add(sub);			
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
		
		
		return subjectsArray;
	}
	
}
