package com.assign.dao;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;

import com.assign.jdbc.JDBC;

public class Security {
	
	private int lecturerID;
	
	public Security(int lecturerID) {
		this.lecturerID = lecturerID;
	}
	
	/**
	 * Validate the signature from a lecturer;
	 * @param signatureResult
	 * @param message
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public boolean verifySignature(String signatureResult, String message) {
		boolean isValid = false;
		
		String publicKey = this.getPublicKey();
		
		
		KeyFactory keyFactory;
		
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
	        X509EncodedKeySpec pubPKCS8 = new X509EncodedKeySpec(publicKeyBytes);
	        PublicKey pubKey = keyFactory.generatePublic(pubPKCS8);
	        
	        Signature signature = Signature.getInstance("SHA1withRSA");
	        signature.initVerify(pubKey);
	        signature.update(message.getBytes());
	        
	        byte[] computedSignature = Base64.getDecoder().decode(signatureResult);
	        
	        
	        isValid = signature.verify(computedSignature);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
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
