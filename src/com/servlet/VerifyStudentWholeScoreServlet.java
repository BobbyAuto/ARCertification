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
		int studentID = student.getStudentID();
		int latestVersion = student.getLatestVersion();
		
		System.out.println("======== VerifyStudentWholeScoreServlet ======== ");
		
		ServletContext servletContext = getServletContext();
		ArrayList<BlockUnit> blockContainer = (ArrayList) servletContext.getAttribute("blockContainer");
		
		if(blockContainer.isEmpty()) {
			
		} else {
			ArrayList<MarkSheet> markSheetsList = verifyStudentInterity(student, blockContainer);
			if (markSheetsList == null) {
				request.setAttribute("verifyStatus", "failed");
			} else {
				request.setAttribute("verifyStatus", "success");
				request.setAttribute("markSheetsList", markSheetsList);
			}
		}
		
		request.getRequestDispatcher("/studentDetails.jsp").forward(request, resp);
		
	}

	/**
	 * verify the student's data integrity
	 * @param student
	 * @param blockContainer
	 * @return a MarkSheet ArrayList if verification pass, otherwise return null.
	 */
	private ArrayList<MarkSheet> verifyStudentInterity(Student student, ArrayList<BlockUnit> blockContainer) {
		ArrayList<MarkSheet> markSheetsList = null;
		
		int latestVersion = student.getLatestVersion();
		int studentID = student.getStudentID();
		
		for(int i=0; i<blockContainer.size(); i++) {
			BlockUnit bu = blockContainer.get(i);
			if(bu.getStudentID() == studentID) {
				ArrayList<MarkSheet> subjectChildren = bu.getSubjectChildren();
				HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
				// if hash verification pass
				if(hos.getHash().equals(bu.getBlockHash())) {
					if(bu.getLatestVersion() == latestVersion) {
						markSheetsList = subjectChildren;
						break;
					} else {
						markSheetsList = subjectChildren;
						continue;
					}
				} else { 
					// TODO if a MarkSheet in subjectChildren was falsified, try to recover it here.
					markSheetsList = null;
					break;
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
