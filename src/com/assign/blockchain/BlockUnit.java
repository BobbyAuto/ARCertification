package com.assign.blockchain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * a block unit
 */
public class BlockUnit implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int latestVersion;
	private String blockHash;
	private String previousHash = "";
	private String timestampe;
	
	private int studentID;
	private int subjectID;
	private int lecturerID;
	
	String message; //structure: lecturerID-subjectID-studentID-numericMark
	String signature;
	
	private ArrayList<MarkSheet> subjectChildren = new ArrayList<MarkSheet>(); // store all subject which was marked and already belonged to a block.

	
	public String getTimestampe() {
		return timestampe;
	}
	public void setTimestampe(LocalDateTime dateTime) {
		
		LocalDateTime currentDateTime = dateTime;

        // Define the date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the current date and time using the formatter
        this.timestampe = currentDateTime.format(formatter);		
	}
	public int getLatestVersion() {
		return latestVersion;
	}
	public void setLatestVersion(int latestVersion) {
		this.latestVersion = latestVersion;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	public ArrayList<MarkSheet> getSubjectChildren() {
		return subjectChildren;
	}
	public void setSubjectChildren(ArrayList<MarkSheet> subjectChildren) {
		this.subjectChildren = subjectChildren;
	}
	public String getBlockHash() {
		return blockHash;
	}
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public int getLecturerID() {
		return lecturerID;
	}
	public void setLecturerID(int lecturerID) {
		this.lecturerID = lecturerID;
	}
}
