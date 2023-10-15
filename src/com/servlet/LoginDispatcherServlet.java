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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.dao.BQuery;
import com.assign.dao.LecturerDao;
import com.assign.dao.StudentDao;
import com.assign.entites.Lecturer;
import com.assign.entites.Student;

/**
 * Servlet implementation class LecturerLoginServlet
 */
@WebServlet("/LoginDispatcherServlet")
public class LoginDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginDispatcherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = (String) request.getParameter("type"); 
		
		System.out.println("======== LoginDispatcherServlet ======== ");
		
		if (type.trim().equals("student")) {
			request.getRequestDispatcher("/StudentLoginServlet").forward(request, resp);
		} else if (type.trim().equals("lecturer")) {
			request.getRequestDispatcher("/LecturerLoginServlet").forward(request, resp);
		} else if(type.trim().equals("empoloyer")) {
//			System.out.println("Empoloyer!");
			String studentName = (String) request.getParameter("studentName"); 
			String studentId = (String) request.getParameter("studentId");
			String email = (String) request.getParameter("email");
			
			if(studentName != null && !studentName.equals("")) {
				StudentDao sd = new StudentDao();
				Student student = sd.findStudent(studentName);
				request.setAttribute("email", email);
				request.setAttribute("student", student);
				request.getRequestDispatcher("/VerifyStudentWholeScoreServlet").forward(request, resp);
			} else {
				request.setAttribute("errorMsg", "Sorry, we could not find this student!");
				request.getRequestDispatcher("/error.jsp").forward(request, resp);
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
