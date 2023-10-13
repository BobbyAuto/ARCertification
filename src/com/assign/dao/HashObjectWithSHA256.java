package com.assign.dao;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.assign.blockchain.BlockUnit;
import com.assign.blockchain.MarkSheet;
import com.assign.blockchain.WriteBlockContainerToFile;
import com.assign.entites.Student;

/**
 * Calculate a hash value of a specific object. Note that the object should be Serialized
 */
public class HashObjectWithSHA256 {
	private Object obj;

	public HashObjectWithSHA256(Object obj) {
		this.obj = obj;
	}

	public String getHash() {
		String hashString = "";

		try {
			// Create a sample object (you can replace this with your own object)
			//Object data = this.obj;

			// Convert the object to a byte array
			byte[] byteData;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(this.obj);
			byteData = bos.toByteArray();

			// Initialize the SHA-256 MessageDigest
			MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

			// Update the digest with the byte array
			sha256Digest.update(byteData);

			// Compute the hash
			byte[] hash = sha256Digest.digest();

			// Convert the hash bytes to a hexadecimal string
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			hashString = hexString.toString();

			//System.out.println("SHA-256 Hash: " + hashString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashString;
	}
	
	public static void main(String[] args) {
		
		
		String str = "Internship2023Weichun Wang2";
		HashObjectWithSHA256 hos = new HashObjectWithSHA256(str);
		String hashString = hos.getHash();
		
		

		
		System.out.println("hashString = " + hashString);
		

	}

}
