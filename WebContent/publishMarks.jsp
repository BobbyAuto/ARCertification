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

<script src="${pageContext.request.contextPath }/js/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath }/js/jsencrypt-3.2.1.js"></script>
<script src="${pageContext.request.contextPath }/js/crypto-js.js"></script>
<script src="${pageContext.request.contextPath }/js/Tools.js"></script>
<script type="text/javascript">
	
	function queryStudents(e) {
		subjectID = e.value;
		
		// Get all the students who have regesited this subject.
		$.ajax({
			  url: "${pageContext.request.contextPath }/studentAndSubjectServlet",
			  type: "POST",
			  dataType: "JSON",
			  data: {
				  subjectID: subjectID
				  },
			  success: function(data) {
					  $("#result").empty(); // Clear any previous results
	                  $.each(data, function(index, sas) {
	                      $("#result").append("<p>studentID: " + sas.studentID + ", fullName: " + sas.fullName + "</p>");
	                  });
			  },
			  error: function(xhr, status, error) {
			    alert("error!");
			  }
			});
	}

</script>
</head>
<body>
<div class="fullName">  Hey, ${fullName}!</div>
<hr />
<div> 
	<div class="subText">Subject: </div>
	<div class="subOption">
		<select onchange=queryStudents(this)>
			<option value="-1">Click to choose a subject.</option>
			<%
			ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects"); 
			for(int i=0; i<subjects.size(); i++) {
			%>
				<option value="<%=subjects.get(i).getSubjectID() %>" ><%= subjects.get(i).getSubjectCode() + " - " + subjects.get(i).getSubjectName() %></option>
			<%
			}
			
			%>
		</select>
	</div>
</div>
<div id="result"></div>



</body>
</html>