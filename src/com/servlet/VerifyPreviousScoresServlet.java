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
import com.assign.blockchain.InternalWrap;
import com.assign.dao.BQuery;
import com.assign.dao.HashObjectWithSHA256;
import com.assign.dao.LecturerDao;
import com.assign.dao.Security;
import com.assign.dao.StudentDao;
import com.assign.entites.Lecturer;
import com.assign.entites.Student;

/**
 * Servlet implementation class VerifyPreviousScoresServlet
 */
@WebServlet("/VerifyPreviousScoresServlet")
public class VerifyPreviousScoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerifyPreviousScoresServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		int lecturerID = Integer.parseInt(request.getParameter("lecturerID"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
//		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		
		StudentDao sd = new StudentDao();
		int latestVersion = sd.getLatestVersion(studentID);
		
		System.out.println("======== VerifyPreviousScoresServlet ======== ");
		
		ServletContext servletContext = getServletContext();
		ArrayList<BlockUnit> blockContainer = (ArrayList) servletContext.getAttribute("blockContainer");
		
        if(blockContainer.isEmpty()) {
        	request.getRequestDispatcher("/BuildAndAddBlockServlet").forward(request, resp);
        } else {
        	
        	// Verify the hash of the closest block
        	BlockUnit lastBu = blockContainer.get(blockContainer.size()-1);
        	if (! new HashObjectWithSHA256(lastBu.getSubjectChildren())
        			.getHash()
        			.equals(lastBu.getBlockHash())
        		) {
        		resp.setContentType("text/html; charset=utf-8");
        		PrintWriter out = resp.getWriter();
        	    out.print("falsified");
        	    out.flush();
        		return;
        	}
        	
        	boolean isFoundSame = false;
        	for(BlockUnit pastBu : blockContainer) {
        		if(pastBu.getStudentID() == studentID && pastBu.getLatestVersion() == latestVersion) {
        			isFoundSame = true;
        			ArrayList<InternalWrap> subjectChildren = pastBu.getSubjectChildren();
        			
        			// Hash all subjects, which are in previous block, together.
            		HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
            		String hashString = hos.getHash();
            		
            		if (pastBu.getBlockHash().equals(hashString)) {
            			System.out.println("--------- Verify Previous Scores Passed! --------");
                		request.getRequestDispatcher("/BuildAndAddBlockServlet").forward(request, resp);
            		} else {
            			resp.setContentType("text/html; charset=utf-8");
                		PrintWriter out = resp.getWriter();
                	    out.print("falsified");
                	    out.flush();
                		return;
            		}
            		break;
        		}
        	}
        	if (isFoundSame == false) {
        		request.getRequestDispatcher("/BuildAndAddBlockServlet").forward(request, resp);
        	}
        }

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
