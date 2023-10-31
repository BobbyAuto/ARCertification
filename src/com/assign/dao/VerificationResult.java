package com.assign.dao;

import java.util.ArrayList;

import com.assign.blockchain.MarkSheet;

/**
 * Store verification result.
 */
public class VerificationResult {

	private ArrayList<MarkSheet> markSheetsList = null;
	private boolean isPassed = false;
	
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
	
	
}
