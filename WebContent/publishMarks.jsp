<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import="java.util.ArrayList" %>  
<%@ page import="com.assign.entites.Subject" %>   
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Publish Marks Here</title>
<link rel="stylesheet" type="text/css" href="css/publishMarks.css">
</head>
<body>
<div class="fullName">  Hey, ${fullName}!</div>
<hr />
<div> 
	<div class="subText">Subject: </div>
	<div class="subOption">
		<select>
			<option value="-1">Click to choose a subject.</option>
			<%
			ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects"); 
			for(int i=0; i<subjects.size(); i++) {
			%>
			
				<option value="<%=subjects.get(i).getSubjectID() %>"><%= subjects.get(i).getSubjectCode() + " - " + subjects.get(i).getSubjectName() %></option>
				
			<%
			}
			
			%>
		</select>
	</div>
</div>
</body>
</html>