<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.assign.blockchain.MarkSheet" %>
<%@ page import="com.assign.entites.Student" %>   
<%@ page import="com.assign.dao.VerificationResult" %> 


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assessment Details</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/all.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.7.0.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		if($("#employer").val() != "") {
			$("#assignAC").hide()
		} else {
			$("#assignAC").click(function() {
				$("#hiddenForm").submit();
			});
		}
		
		
		
	});
</script>
</head>
<body>
<div id="headContainer">
	<p id="sdLeft">Assessment Details</p>
	<p id="sdMiddle">${verifyStatus}</p>
	<p id="sdRight">${student.getFullName() }</p>
</div>
<div id="assignAC">Assign Access Code for Third Parties</div>
 
	<table id="tbDetails">
		<thead>
			<tr>
				<th>Index</th>
				<th>Subject Code</th>
				<th>Subject</th>
				<th>Score</th>
			</tr>
		</thead>
		<tbody>
			<%
			
			VerificationResult veriResult = (VerificationResult) request.getAttribute("veriResult");
			ArrayList<MarkSheet> markSheetsList = veriResult.getMarkSheetsList();
			boolean isPass = veriResult.isPassed();
			for(int i=0; i<markSheetsList.size(); i++) {
				MarkSheet markSheet = markSheetsList.get(i);
				String[] text = markSheet.getSubjectText().split("-");
				if (isPass == false && i == markSheetsList.size()-1) {
					%>
					<tr style='color: red;'>
						<td><%=i+1 %></td>
						<td><%=text[0] %></td>
						<td><%=text[1] %></td>
						<td><%=markSheet.getScore() %></td>
					</tr>
					<%
				} else {
				%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=text[0] %></td>
					<td><%=text[1] %></td>
					<td><%=markSheet.getScore() %></td>
				</tr>
				<%
				}
			}
			%>
		</tbody>
	</table>
<form id="hiddenForm" method="post" action="assignAccessCode.jsp">
	<input type="hidden" name="studentID" value="${student.getStudentID() }" />
	<input type="hidden" id="employer" name="employer" value="${email }" />
</form>
	
</body>
</html>