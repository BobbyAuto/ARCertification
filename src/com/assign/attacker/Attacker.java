package com.assign.attacker;

import java.util.ArrayList;

import com.assign.blockchain.BlockUnit;
import com.assign.blockchain.MarkSheet;
import com.assign.blockchain.WriteBlockContainerToFile;
import com.assign.dao.StudentDao;
import com.assign.entites.Student;

public class Attacker {
	
	private ArrayList<BlockUnit> blockContainer;
	private String contextPath = "/Users/wangweichun/MyTomcat/webapps/ARCertification/"; 
	
	public Attacker() {
		
        
        WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(this.contextPath);
        ArrayList<BlockUnit> blockContainer = wbct.readBlockContainer();
        
        if (blockContainer == null) {
        	System.out.println("Data not found!");
        } else {
        	this.blockContainer = blockContainer;
        }
	}

	
	public void tamperCourseScore(int studentID) {
		StudentDao sd = new StudentDao();

		int latestVersion = sd.getLatestVersion(studentID);
		
		for (int i = 0; i < blockContainer.size(); i++) {
			BlockUnit bu = blockContainer.get(i);
			if (bu.getStudentID() == studentID && bu.getLatestVersion() == latestVersion-1) {
				System.out.println("target found.");
				
				ArrayList<MarkSheet> courseContainer = bu.getSubjectChildren();
//				MarkSheet ms = courseContainer.get(courseContainer.size() - 1);
				MarkSheet ms = courseContainer.get(0);
//				ms.setScore(83);
				ms.setScore(96);
				
				WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(this.contextPath, this.blockContainer);
				wbct.writeBlockContainer();
				System.out.println("the score has been tampered with.");
			}
		}
	}

	public static void main(String[] args) {
		Attacker attacker = new Attacker();
		attacker.tamperCourseScore(1);

	}

}
