package com.assign.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.assign.jdbc.JDBC;

public class Security {
	
	private int lecturerID;
	
	public Security(int lecturerID) {
		this.lecturerID = lecturerID;
	}
	
	/**
	 * Validate the signature from a lecturer;
	 * @param signature
	 * @param message
	 * @return
	 */
	public boolean verifySignature(String signature, String message) {
		boolean isValid = true;
		
		String publicKey = this.getPublicKey();
		
		
		
		return isValid;
	}
	
	
	/**
	 * Get a lecturer's public key.
	 * @return
	 */
	private String getPublicKey() {
		String publicKey = "";
		
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			statement = JDBC.getStatement();
			String sql = "SELECT publicKey FROM Lecturer where lecturerID = " + this.lecturerID;
			rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				publicKey = rs.getString("publicKey");

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
		
		
		return publicKey;
	}

}
