package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.blockchain.BlockUnit;
import com.assign.blockchain.MarkSheet;
import com.assign.dao.BQuery;
import com.assign.dao.HashObjectWithSHA256;
import com.assign.dao.LecturerDao;
import com.assign.dao.Security;
import com.assign.dao.StudentDao;
import com.assign.entites.Lecturer;
import com.assign.entites.Student;

/**
 * Servlet implementation class VerifyStudentWholeScoreServlet
 */
@WebServlet("/VerifyStudentWholeScoreServlet")
public class VerifyStudentWholeScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		ArrayList<BlockUnit> blockContainer = (ArrayList) servletContext.getAttribute("blockContainer");

		if (blockContainer.isEmpty()) {

		} else {
			ArrayList<MarkSheet> markSheetsList = verifyStudentInterity(student, blockContainer);
			request.setAttribute("student", student);
			if (markSheetsList == null) {
				request.setAttribute("verifyStatus", "<span style='color:red;'>Verification Failed</span>");
			} else {
				request.setAttribute("verifyStatus", "<span style='color:green;'>Verification Successful</span>");
				request.setAttribute("markSheetsList", markSheetsList);
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
	private ArrayList<MarkSheet> verifyStudentInterity(Student student, ArrayList<BlockUnit> blockContainer) {
		ArrayList<MarkSheet> markSheetsList = null;
		boolean isPassed = true;

		int latestVersion = student.getLatestVersion();
		int studentID = student.getStudentID();

		for (int i = 0; i < blockContainer.size(); i++) {
			BlockUnit bu = blockContainer.get(i);
			if (bu.getStudentID() == studentID) {
				ArrayList<MarkSheet> subjectChildren = bu.getSubjectChildren();
				HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
				// --- 1.--- verify the hash of the container of subjectChildren
				if (hos.getHash().equals(bu.getBlockHash()) == false) {
					markSheetsList = null;
					isPassed = false;
					break;
				}

				// --- 2.--- verify the hash of current block header is equals with the previous hash in the next block header.
				if (i + 1 < blockContainer.size()) {
					BlockUnit buNext = blockContainer.get(i + 1);
					if (new HashObjectWithSHA256(bu).getHash().equals(buNext.getPreviousHash()) == false) {
						markSheetsList = null;
						isPassed = false;
						break;
					}
				}
				
				// --- 3.--- verify lecturer's signature
				Security se = new Security(bu.getLecturerID());
				String message = bu.getMessage();
				if (se.verifySignature(bu.getSignature(), message) == false) {
					markSheetsList = null;
					isPassed = false;
					break;
				}

				// --- 4.--- compare if the score involved in the message, is equal to the score
				// stored in the course data.
				String[] msgFragment = message.split("-");
				float score_msg = Float.parseFloat(msgFragment[msgFragment.length - 1]);
				float score_course = subjectChildren.get(subjectChildren.size()-1).getScore();
				if(score_msg != score_course) {
					markSheetsList = null;
					isPassed = false;
					break;
				}
				

				if (bu.getLatestVersion() == latestVersion) {
					markSheetsList = subjectChildren;
					break;
				} else {
					markSheetsList = subjectChildren;
					continue;
				}

			}
		}
		return markSheetsList;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

}
