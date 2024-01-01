package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.blockchain.BlockUnit;
import com.assign.blockchain.MarkSheet;
import com.assign.blockchain.WriteBlockContainerToFile;
import com.assign.dao.HashObjectWithSHA256;
import com.assign.dao.Security;
import com.assign.dao.VerificationResult;
import com.assign.entites.Student;

/**
 * Servlet implementation class VerifyStudentWholeScoreServlet
 */
@WebServlet("/VerifyStudentWholeScoreServlet")
public class VerifyStudentWholeScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private String contextPath;
	private boolean doTrytoRecover = true;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyStudentWholeScoreServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		Student student = (Student) request.getAttribute("student");

		System.out.println("======== VerifyStudentWholeScoreServlet ======== ");

		ServletContext servletContext = getServletContext();
		this.contextPath = servletContext.getRealPath("/");
        
        WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(this.contextPath);
        ArrayList<BlockUnit> blockContainer = wbct.readBlockContainer();
		
		
//		ArrayList<BlockUnit> blockContainer = (ArrayList) servletContext.getAttribute("blockContainer");

		if (blockContainer.isEmpty()) {

		} else {
			VerificationResult veriResult = verifyStudentInterity(student, blockContainer);
//			ArrayList<MarkSheet> markSheetsList = veriResult.getMarkSheetsList();
			request.setAttribute("student", student);
			if (veriResult.isPassed() == false) {
				request.setAttribute("verifyStatus", "<span style='color:red;'>Verification Failed!!</span>");
				request.setAttribute("veriResult", veriResult);
			} else {
				request.setAttribute("verifyStatus", "<span style='color:green;'>Verification Successful</span>");
				request.setAttribute("veriResult", veriResult);
			}
		}

		request.getRequestDispatcher("/studentDetails.jsp").forward(request, resp);

	}

	/**
	 * verify the student's data integrity
	 * 
	 * @param student
	 * @param blockContainer
	 * @return a MarkSheet ArrayList if verification pass, otherwise return null.
	 */
	private VerificationResult verifyStudentInterity(Student student, ArrayList<BlockUnit> blockContainer) {

		int latestVersion = student.getLatestVersion();
		int studentID = student.getStudentID();
		
		VerificationResult vr = new VerificationResult();
		vr.setPassed(true);

		for (int i = 0; i < blockContainer.size(); i++) {
			BlockUnit bu = blockContainer.get(i);
			if (bu.getStudentID() == studentID) {
				ArrayList<MarkSheet> subjectChildren = bu.getSubjectChildren();
				MarkSheet markSheet = subjectChildren.get(subjectChildren.size()-1); // get the last mark sheet.
				
				if (bu.getLatestVersion() == latestVersion) {
					vr.getMarkSheetsList().add(markSheet);
					
					// if the verification is failed, go to recovery module.
					if(vr.isPassed() == false) { 
						System.out.println("a course data was tampered with !!");
						if(this.tryToRecover(student, vr.getTamperedVersion(), latestVersion, blockContainer) == true) { // if successfully recovered, then go to verify again.
							vr = verifyStudentInterity(student, blockContainer);
						}
					}
					
					break;
				} 
				
				
				HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
				// --- 1.--- verify the hash of the container of subjectChildren
				if (hos.getHash().equals(bu.getBlockHash()) == false) {
					
					vr.setPassed(false);
					vr.getMarkSheetsList().add(markSheet);
					
					if(vr.getTamperedVersion() == -1) {
						vr.setTamperedVersion(bu.getLatestVersion());
					}
					
					continue;
				}

				// --- 2.--- verify the hash of current block header is equals with the previous hash in 
				// the next block header.
				if (i + 1 < blockContainer.size()) {
					BlockUnit buNext = blockContainer.get(i + 1);
					if (new HashObjectWithSHA256(bu).getHash().equals(buNext.getPreviousHash()) == false) {
						
						vr.setPassed(false);
						vr.getMarkSheetsList().add(markSheet);
						if(vr.getTamperedVersion() == -1) {
							vr.setTamperedVersion(bu.getLatestVersion());
						}
						continue;
					}
				}
				
				// --- 3.--- verify lecturer's signature
				Security se = new Security(bu.getLecturerID());
				String message = bu.getMessage();
				if (se.verifySignature(bu.getSignature(), message) == false) {

					vr.setPassed(false);
					vr.getMarkSheetsList().add(markSheet);
					if(vr.getTamperedVersion() == -1) {
						vr.setTamperedVersion(bu.getLatestVersion());
					}
					continue;
				}

				// --- 4.--- compare if the score involved in the message, is equal to the score
				// stored in the course data.
				String[] msgFragment = message.split("-");
				float score_msg = Float.parseFloat(msgFragment[msgFragment.length - 1]);
				float score_course = subjectChildren.get(subjectChildren.size()-1).getScore();
				if(score_msg != score_course) {
					
					vr.setPassed(false);
					vr.getMarkSheetsList().add(markSheet);
					if(vr.getTamperedVersion() == -1) {
						vr.setTamperedVersion(bu.getLatestVersion());
					}
					continue;
				}
				
				vr.getMarkSheetsList().add(markSheet);
				
			}
		}
		return vr;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}
	
	/**
	 * Try to recover the tampered data.
	 * @param latestVersion 
	 * @param currentVersion 
	 * @param studentID 
	 * @return
	 */
	protected boolean tryToRecover(Student student, int currentVersion, int latestVersion, ArrayList<BlockUnit> blockContainer) {
		int studentID = student.getStudentID();
		boolean isrecovered = true;
		
		// if the recovery module is disabled, then return false
		if (this.doTrytoRecover == false) { 
			isrecovered = false;
			
		// start the recovery process
		} else {
			System.out.println("start trying to recovery....");
			for (int i = 0; i < blockContainer.size(); i++) {
				BlockUnit bu = blockContainer.get(i);
				//find the related block, which is tampered with.
				if (bu.getStudentID() == studentID && bu.getLatestVersion() == currentVersion) { 
					Security se = new Security(bu.getLecturerID());
					String message = bu.getMessage();
					// if signature is integrity, then recover the score from message.
					if (se.verifySignature(bu.getSignature(), message) == true) {
						String[] messageFragment = message.split("-");
						float messageScore = Float.parseFloat(messageFragment[messageFragment.length-1]);
						
						ArrayList<MarkSheet> subjectChildren = bu.getSubjectChildren();
						MarkSheet markSheet = subjectChildren.get(subjectChildren.size()-1); // get the last mark sheet.
						markSheet.setScore(messageScore);
						
						HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
						// --- 1.--- check the hash of the container of subjectChildren
						if (hos.getHash().equals(bu.getBlockHash()) == true) {
							// write data into file.
							WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(this.contextPath, blockContainer);
							wbct.writeBlockContainer();
							
							System.out.println("recovery finished.");
						} else {
							isrecovered = false;
						}
						
						
						break;
					} else {
						isrecovered = false;
					}
				}
			}
		}
		

		return isrecovered;
		
		
	}

}
