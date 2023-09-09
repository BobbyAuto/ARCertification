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
import com.assign.entites.Lecturer;
import com.assign.entites.Student;

/**
 * Servlet implementation class LecturerLoginServlet
 */
@WebServlet("/StudentLoginServlet")
public class StudentLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentLoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = (String) request.getParameter("username"); 
		String password = (String) request.getParameter("password");
		
		System.out.println("======== StudentLoginServlet ======== ");
		
		request.getRequestDispatcher("/studentDetails.jsp").forward(request, resp);

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
