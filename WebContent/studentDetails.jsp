<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.assign.blockchain.MarkSheet" %>
<%@ page import="com.assign.entites.Student" %>   


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assessment Details</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/all.css">

</head>
<body>
<div id="headContainer">
	<p id="sdLeft">Assessment Details</p>
	<p id="sdMiddle">${verifyStatus}</p>
	<p id="sdRight">${student.getFullName() }</p>
</div>
<!-- 
<span>${verifyStatus}</span>
<span>${markSheetsList.size() }</span>
 -->
 
	<table id="tbDetails">
		<thead>
			<tr>
				<th>Subject Code</th>
				<th>Subject</th>
				<th>Score</th>
				
			</tr>
		</thead>
		<tbody>
			<%
			ArrayList<MarkSheet> markSheetsList = (ArrayList<MarkSheet>) request.getAttribute("markSheetsList");
			for(int i=0; i<markSheetsList.size(); i++) {
				MarkSheet markSheet = markSheetsList.get(i);
				String[] text = markSheet.getSubjectText().split("-");
				
				%>
				<tr>
					<td><%=text[0] %></td>
					<td><%=text[1] %></td>
					<td><%=markSheet.getScore() %></td>
				</tr>
				<%
			}
			%>
		</tbody>
	</table>
	
	
</body>
</html>