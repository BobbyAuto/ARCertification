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
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.blockchain.BlockUnit;
import com.assign.blockchain.InternalWrap;
import com.assign.dao.BQuery;
import com.assign.dao.HashObjectWithSHA256;
import com.assign.dao.LecturerDao;
import com.assign.dao.Security;
import com.assign.entites.Lecturer;
import com.assign.entites.Student;

/**
 * Servlet implementation class LecturerLoginServlet
 */
@WebServlet("/BuildAndAddBlockServlet")
public class BuildAndAddBlockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuildAndAddBlockServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		
		int lecturerID = Integer.parseInt(request.getParameter("lecturerID"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String studentName = request.getParameter("studentName");
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		String subjectText = request.getParameter("subjectText");
		
		float score = Float.parseFloat(request.getParameter("score"));
		
		System.out.println("======== BuildAndAddBlockServlet ======== ");
		System.out.println("studentID = " + studentID);
		
		// internal data, which should be integrity and will be hashed.
		InternalWrap interW = new InternalWrap();
		interW.setStudentID(studentID);
		interW.setStudentName(studentName);
		interW.setSubjectID(subjectID);
		interW.setSubjectText(subjectText);
		interW.setScore(score);
		
		// Hash SHA-256
		HashObjectWithSHA256 hos = new HashObjectWithSHA256(interW);
		String hashString = hos.getHash();
		
		BlockUnit bu = new BlockUnit();
		bu.setStudentID(studentID);
		bu.setSubjectID(subjectID);
		bu.setBlockHash(hashString);
		bu.getSubjectChildren().add(interW);
		
		interW.setDirectParent(bu); // belong to a block parent.
		
		System.out.println("chain studentID = " + bu.getSubjectChildren().get(0).getDirectParent().getStudentID());
		
		
		ServletContext servletContext = getServletContext();
		HashMap ymc = (HashMap) servletContext.getAttribute("yearMapContainer");
		ymc.put("2023", "test map container");
		System.out.println(ymc.get("2023"));
		
				
		PrintWriter out = resp.getWriter();
	    out.print("{\"msg\":success}");
	    out.flush();
		return;
		

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
