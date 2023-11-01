package com.assign.dao;

import java.util.ArrayList;

import com.assign.blockchain.MarkSheet;

/**
 * Store verification result.
 */
public class VerificationResult {

	private ArrayList<MarkSheet> markSheetsList = new ArrayList<MarkSheet>();
	private boolean isPassed = false;
	private int tamperedVersion = -1; // the latestVersion number in the block header, which is tampered with.
	
	public ArrayList<MarkSheet> getMarkSheetsList() {
		return markSheetsList;
	}
	public void setMarkSheetsList(ArrayList<MarkSheet> markSheetsList) {
		this.markSheetsList = markSheetsList;
	}
	
	public boolean isPassed() {
		return isPassed;
	}
	public void setPassed(boolean isPassed) {
		this.isPassed = isPassed;
	}
	public int getTamperedVersion() {
		return tamperedVersion;
	}
	public void setTamperedVersion(int tamperedVersion) {
		this.tamperedVersion = tamperedVersion;
	}
	
	
}
