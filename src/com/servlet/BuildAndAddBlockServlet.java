package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.blockchain.BlockUnit;
import com.assign.blockchain.MarkSheet;
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
		ServletContext servletContext = getServletContext();
		ArrayList<BlockUnit> blockContainer = (ArrayList) servletContext.getAttribute("blockContainer");
		
		int lecturerID = Integer.parseInt(request.getParameter("lecturerID"));
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String studentName = request.getParameter("studentName");
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		String subjectText = request.getParameter("subjectText");
		
		String message = (String) request.getParameter("message"); 
		String signature = (String) request.getParameter("signature");
		
		float score = Float.parseFloat(request.getParameter("score"));
		
		StudentDao sd = new StudentDao();
		int latestVersion = sd.getLatestVersion(studentID);
		int newVersion = latestVersion + 1;
		
		System.out.println("======== BuildAndAddBlockServlet ======== ");
		
		// internal data, which should be integrity and will be hashed.
		MarkSheet markSheet = new MarkSheet();
		markSheet.setStudentID(studentID);
		markSheet.setStudentName(studentName);
		markSheet.setSubjectID(subjectID);
		markSheet.setSubjectText(subjectText);
		markSheet.setScore(score);
		markSheet.setLecturerID(lecturerID);
		
		BlockUnit bu = new BlockUnit();
		if(!blockContainer.isEmpty()) {
			bu = blockContainer.get(blockContainer.size()-1); // get the empty tail block.
		}
		bu.setStudentID(studentID);
		bu.setSubjectID(subjectID);
		bu.setLecturerID(lecturerID);
		bu.setLatestVersion(newVersion);
		bu.setTimestampe(LocalDateTime.now()); // set the current system timestampe.
		bu.setMessage(message);
		bu.setSignature(signature);
				
		StudentAndSubjectDao ssd = new StudentAndSubjectDao();
		
        if(blockContainer.isEmpty()) {
        	bu.getSubjectChildren().add(markSheet);
        	// Hash the current subject.
        	ArrayList<MarkSheet> subjectChildren = bu.getSubjectChildren();
    		HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
    		String hashString = hos.getHash();
    		bu.setBlockHash(hashString);
    		blockContainer.add(bu); // add new block to container.
    		
        } else {
        	boolean isFoundSame = false;
        	// find the latest block of the same Student
        	for(BlockUnit pastBu : blockContainer) {
        		if(pastBu.getStudentID() == studentID && pastBu.getLatestVersion() == latestVersion) {
        			bu.getSubjectChildren().addAll(pastBu.getSubjectChildren());
        			bu.getSubjectChildren().add(markSheet);
        			
        			ArrayList<MarkSheet> subjectChildren = bu.getSubjectChildren();
        			// Hash all subjects, which are in previous block, together.
            		HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
            		String hashString = hos.getHash();
            		bu.setBlockHash(hashString);   
            		
            		isFoundSame = true;
            		break;
        		}
        	}
        	if(!isFoundSame) {
        		// A new Student who had never been added into the block.
        		bu.getSubjectChildren().add(markSheet);
            	// Hash the current subject.
            	ArrayList<MarkSheet> subjectChildren = bu.getSubjectChildren();
        		HashObjectWithSHA256 hos = new HashObjectWithSHA256(subjectChildren);
        		String hashString = hos.getHash();
        		bu.setBlockHash(hashString);
        	}
        }        
        
        
		sd.updateLatestVersion(studentID, newVersion); // update the latestVersion in JDBC table;
		ssd.updateScore(studentID, subjectID, score); // update the subject score of the student in JDBC table
		
		// create an empty block to store the hash value of the current block, append this empty block to the tail of the chain.
		BlockUnit buTail = new BlockUnit();
		buTail.setPreviousHash(new HashObjectWithSHA256(bu).getHash());
		blockContainer.add(buTail);
		
		/* save the block container into a file.*/
		String contextPath = getServletContext().getRealPath("/");
		WriteBlockContainerToFile wbct = new WriteBlockContainerToFile(contextPath, blockContainer);
		wbct.writeBlockContainer();
		
        resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
	    out.print("success");
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
