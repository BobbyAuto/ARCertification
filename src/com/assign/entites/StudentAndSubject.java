package com.assign.entites;

public class StudentAndSubject {
	private int studentID;
	private int subjectID;
	private String score;
	private String fullName;
	private String nickName;
	private int latestVersion;
	
	
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
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getLatestVersion() {
		return latestVersion;
	}
	public void setLatestVersion(int latestVersion) {
		this.latestVersion = latestVersion;
	}
	
	
}
