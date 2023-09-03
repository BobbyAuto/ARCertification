package com.assign.dao;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;

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

			System.out.println("SHA-256 Hash: " + hashString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashString;
	}
	
	public static void main(String[] args) {
		Student s = new Student();
		s.setFullName("Weichun");
		
		HashObjectWithSHA256 hos = new HashObjectWithSHA256(s);
		String hashString = hos.getHash();
		
		Student s2 = new Student();
		s2.setFullName("Weichun");
		//s2.setLatestVersion(2);
		HashObjectWithSHA256 hos2 = new HashObjectWithSHA256(s2);
		String hashString2 = hos2.getHash();

		
		System.out.println("hashString = " + hashString);
		System.out.println("hashString2 = " + hashString2);
		System.out.println(hashString.equals(hashString2));

	}

}
