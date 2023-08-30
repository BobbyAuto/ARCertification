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
import com.assign.entites.Student;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String msg = (String) request.getParameter("msg"); 
		String sign = (String) request.getParameter("sign"); 
		String publicKeyString = (String)request.getParameter("publicKey");
		///String publicKeyString = "MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHO8lxrizZisNpk6UFlcBk5wXD0AQcOOEDgCKtWo60oYHgU5WtNV7IxwGnDqDlGEoSEGYnpgWxljmVI/Jhfs9UK1jjOkZHgs6ajJffNLitC/E0EOqQ/WBimJF0RV0yWpvGzSXmc1J151xhag4pxK+QK1j+RNGbHLVeS7vkwuk+CvAgMBAAE=";
		
		System.out.println("msg = " + msg);
		System.out.println("sign = " + sign);
		System.out.println("publicKeyString = " + publicKeyString);
		
		byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        KeyFactory keyFactory = null;
        PublicKey publicKey = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));
			
			Signature sig = Signature.getInstance("SHA256withRSA");
	        sig.initVerify(publicKey);
	        sig.update(msg.getBytes());

	        byte[] signatureBytes = Base64.getDecoder().decode(sign);
	        boolean verified = sig.verify(signatureBytes);
	        
	        if (verified) {
	            System.out.println("Signature is valid");
	        } else {
	            System.out.println("Signature is not valid");
	        }
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("gb2312");
		
		PrintWriter printWriter = resp.getWriter();
		
			BQuery query = new BQuery();
			
			ArrayList<Student> students = (ArrayList<Student>) query.queryStudents();
			
			printWriter.print("<html><head><title>Servlet连接数据库</title></head>");
			printWriter.print("<body><table border=1 align=\"center\"><tr>id<td></td><td>name</td><td>subject_ids</td></tr>");
			
			for (Student s : students) {
				
				printWriter.print("<tr>");
				printWriter.print("<td>" + s.getId() + "</td>");
				printWriter.print("<td>" + s.getName() + ", fantastic!!!</td>");
				printWriter.print("<td>" + s.getSubject_ids() + "</td>");
				printWriter.print("</tr>");
			}
			printWriter.print("</table>");
			printWriter.print("</html>");

		
		printWriter.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
