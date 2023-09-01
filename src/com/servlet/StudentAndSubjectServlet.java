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
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assign.dao.BQuery;
import com.assign.dao.LecturerDao;
import com.assign.dao.SubjectsDao;
import com.assign.entites.Lecturer;
import com.assign.entites.Student;
import com.assign.entites.Subject;

/**
 * Servlet implementation class StudentAndSubjectServlet
 */
@WebServlet("/StudentAndSubjectServlet")
public class StudentAndSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentAndSubjectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String subjectID = request.getParameter("subjectID"); 
		
		System.out.println("======== PublishMarksServlet ======== ");
		System.out.println("subjectID = " + subjectID);

		((ServletResponse) resp).setContentType("application/json;charset=UTF-8");
//		设置响应类型及编码格式为json文件格式
		
//		{"code":0,"msg":"用户名或密码不能为空！"}
//		得到输出流
		PrintWriter out = resp.getWriter();
//		输出结果
		out.write("{\"code\":1,\"msg\":\"success\"}");
		
		out.close();
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
