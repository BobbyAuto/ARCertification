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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
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
import com.assign.blockchain.WriteBlockContainerToFile;
import com.assign.dao.BQuery;
import com.assign.dao.HashObjectWithSHA256;
import com.assign.dao.LecturerDao;
import com.assign.dao.Security;
import com.assign.dao.StudentAndSubjectDao;
import com.assign.dao.StudentDao;
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
		
		StudentDao sd = new StudentDao();
		int latestVersion = sd.getLatestVersion(studentID);
		int newVersion = latestVersion + 1;
		
		System.out.println("======== BuildAndAddBlockServlet ======== ");
		System.out.println("studentID = " + studentID);
		
		// internal data, which should be integrity and will be hashed.
		InternalWrap interW = new InternalWrap();
		interW.setStudentID(studentID);
		interW.setStudentName(studentName);
		interW.setSubjectID(subjectID);
		interW.setSubjectText(subjectText);
		interW.setScore(score);
		
		BlockUnit bu = new BlockUnit();
		bu.setStudentID(studentID);
		bu.setSubjectID(subjectID);
		bu.setLatestVersion(newVersion);
		
		interW.setDirectParent(bu); // belong to a block parent.		
		
		ServletContext servletContext = getServletContext();
		ArrayList<BlockUnit> blockContainer = (ArrayList) servletContext.getAttribute("blockContainer");
		
		StudentAndSubjectDao ssd = new StudentAndSubjectDao();
        
        if(blockContainer.isEmpty()) {
        	// Hash the current subject.
    		HashObjectWithSHA256 hos = new HashObjectWithSHA256(interW);
    		String hashString = hos.getHash();
    		bu.setBlockHash(hashString);
    		bu.getSubjectChildren().add(interW);
        } else { 
        	boolean isFoundSame = false;
        	// find the latest block of the same Student
        	for(BlockUnit pastBu : blockContainer) {
        		if(pastBu.getStudentID() == studentID && pastBu.getLatestVersion() == latestVersion) {
        			bu.getSubjectChildren().addAll(pastBu.getSubjectChildren());
        			bu.getSubjectChildren().add(interW);
        			
        			ArrayList<InternalWrap> subjectChildren = bu.getSubjectChildren();
        			// Hash all subjects, which are in previous block, together.
            		HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
            		String hashString = hos.getHash();
            		bu.setBlockHash(hashString);   
            		
            		isFoundSame = true;
            		break;
        		}
        	}
        	if(isFoundSame == true) {
        		BlockUnit lastBlock = blockContainer.get(blockContainer.size()-1); // get the last Block, then get the previous hash;
            	bu.setPreviousHash(lastBlock.getBlockHash());
        	} else { 
        		// A new Student who had never been added into the block.
        		
        		// Hash the current subject.
        		HashObjectWithSHA256 hos = new HashObjectWithSHA256(interW);
        		String hashString = hos.getHash();
        		bu.setBlockHash(hashString);
        		bu.getSubjectChildren().add(interW);
        		
        		BlockUnit lastBlock = blockContainer.get(blockContainer.size()-1); // get the last Block, then get the previous hash;
            	bu.setPreviousHash(lastBlock.getBlockHash());
        	}
        }        
        bu.setTimestampe(LocalDateTime.now()); // set the current system timestampe.
        blockContainer.add(bu); // add new block to container.
		sd.updateLatestVersion(studentID, newVersion); // update the latestVersion in JDBC table;
		ssd.updateScore(studentID, subjectID, score); // update the subject score of the student in JDBC table
		
		/* save the block container into a file.*/
		String contextPath = getServletContext().getRealPath("/");
		WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(contextPath, blockContainer);
		wbct.writeBlockContainer();
        
        /* validation here start */
        System.out.println("------------- here is validation ----------------");
        BlockUnit lastBlockValidate = blockContainer.get(blockContainer.size()-1);
        ArrayList<InternalWrap> children = lastBlockValidate.getSubjectChildren();
        for(InternalWrap inw : children) {
        	System.out.println(inw);
        }
        System.out.println(" ");
				
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
