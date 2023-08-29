package com.assign.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.assign.entites.Student;
import com.assign.jdbc.JDBC;

public class BQuery {

	public ArrayList<Student> queryStudents() {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		ArrayList<Student> students = new ArrayList();

		try {
			statement = JDBC.getStatement();
			String sql = "SELECT * FROM Student";
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				
				Student student = new Student();
				student.setId(resultSet.getInt("id"));
				student.setName(resultSet.getString("name"));
				
				student.setSubject_ids(resultSet.getString("subject_ids"));

				students.add(student);
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {

					statement.close();

					resultSet = null;
				}

				if (connection != null) {
					connection.close();
					connection = null;
				}
				
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return students;
	}
}
