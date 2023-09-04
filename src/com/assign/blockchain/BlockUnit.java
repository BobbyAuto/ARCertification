package com.assign.blockchain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * a block unit
 */
public class BlockUnit implements Serializable{
	private static final long serialVersionUID = 1L;
	private int studentID;
	private int subjectID;
	private ArrayList<InternalWrap> subjectChildren = new ArrayList<InternalWrap>(); // store all subject which was marked and already belonged to a block.
	private String blockHash;
	private String previousHash;
	private int latestVersion;
	
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
	public ArrayList<InternalWrap> getSubjectChildren() {
		return subjectChildren;
	}
	public void setSubjectChildren(ArrayList<InternalWrap> subjectChildren) {
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
}
