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
		BlockUnit bu = new BlockUnit();
		bu.setStudentID(1);
		bu.setSubjectID(1);
		bu.setTimestampe(LocalDateTime.now());
		
		MarkSheet intw = new MarkSheet();
		intw.setStudentID(1);
		intw.setStudentName("weichun");
		intw.setSubjectID(1);
		intw.setScore(68);
//		intw.setDirectParent(bu);
		
		bu.getSubjectChildren().add(intw);
		
		ArrayList<MarkSheet> children = bu.getSubjectChildren();
		
		
		HashObjectWithSHA256 hos = new HashObjectWithSHA256(children);
		String hashString = hos.getHash();
		
		ArrayList<BlockUnit> blockContainer = new ArrayList<BlockUnit> ();
		blockContainer.add(bu);
		
		String contextPath = "/Users/wangweichun/MyTomcat/webapps/ARCertification/";
		WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(contextPath, blockContainer);
		wbct.writeBlockContainer();
		
		
		
		ArrayList<BlockUnit> blockContainer2 = wbct.readBlockContainer();
		ArrayList<MarkSheet> children2 = blockContainer2.get(0).getSubjectChildren();
		
		//s2.setLatestVersion(2);
		HashObjectWithSHA256 hos2 = new HashObjectWithSHA256(children2);
		String hashString2 = hos2.getHash();

		
		System.out.println("hashString = " + hashString);
		System.out.println("hashString2 = " + hashString2);
		System.out.println(hashString.equals(hashString2));

	}

}
