package com.assign.blockchain;

import java.io.Serializable;

public class InternalWrap implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int studentID;
	private String studentName;
	private int subjectID;
	private String subjectText;
	private float score;
	private BlockUnit directParent;
	
	
	public BlockUnit getDirectParent() {
		return directParent;
	}
	public void setDirectParent(BlockUnit directParent) {
		this.directParent = directParent;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getSubjectID() {
		return subjectID;
	}
	public void setSubjectID(int subjectID) {
		this.subjectID = subjectID;
	}
	
	public String getSubjectText() {
		return subjectText;
	}
	public void setSubjectText(String subjectText) {
		this.subjectText = subjectText;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	
}
