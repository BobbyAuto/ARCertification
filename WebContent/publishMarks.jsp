<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import="java.util.ArrayList" %>  
<%@ page import="com.assign.entites.Subject" %>   
 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Publish Marks Here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/publishMarks.css">

<script src="${pageContext.request.contextPath }/js/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath }/js/jsencrypt-3.2.1.js"></script>
<script src="${pageContext.request.contextPath }/js/crypto-js.js"></script>
<script src="${pageContext.request.contextPath }/js/Tools.js"></script>
<script type="text/javascript">
	
	var subjectText = ""; 
	
	function queryStudents(e) {
		subjectID = e.value;
		
		var selectedOption = e.options[e.selectedIndex];
		subjectText = selectedOption.textContent;
		
		// Get all the students who have regesited this subject.
		$.ajax({
			  url: "${pageContext.request.contextPath }/studentAndSubjectServlet",
			  type: "POST",
			  dataType: "JSON",
			  data: {
				  subjectID: subjectID
				  },
			  success: function(data) {
				  	var tbody = $('#studentsList tbody'); 
				  	tbody.empty(); // Clear any previous results
	                  $.each(data, function(index, sas) {
	                      var scoreColumn = "";
	                      markColumn = "";
	                      var idValue = sas.fullName + "-" + sas.studentID; //Store student name into id property.
	                      var idValue_score = sas.studentID + "-" + subjectID;
	                      var idValue_operation = sas.studentID + "-" + subjectID + "-o";
	                      if (sas.score == "-1") {
	                    	  scoreColumn = "<td id='" + idValue_score + "'>Waiting for Mark</td>"
	                    	  //alert(scoreColumn);
	                    	  markColumn = "<td id='" + idValue_operation + "'><a id='" + idValue + "' href='#' onclick=goMark(this," + sas.studentID + "," + subjectID + ")>go to mark</a></td>";
	                      		//alert(markColumn);
	                      } else {
	                    	  scoreColumn = "<td style='color: green'>" + sas.score + "</td>";
	                    	  markColumn = "<td style='color: gray'>MARKED!</td>";
	                      }
	                      tbody.append("<tr><td>" + sas.studentID + "</td><td>" + sas.fullName + "</td>" + scoreColumn + markColumn + "</tr>");
	                  });
			  },
			  error: function(xhr, status, error) {
			    alert("error!");
			  }
			});
	}
	
	function goMark(e, studentID, subjectID) {
		var studentName = e.id.split("-")[0]; // get studentName from id value of <a> element.
		
		var mark=prompt("Give a mark here","");
		if (mark!=null && mark!="") {
			var numericMark = parseFloat(mark);
			if (!isNaN(numericMark) && numericMark >= 1 && numericMark <= 100) {
				var lecturerID = ${lecturerID};
	            var privateKey = ""; 
	            var message = studentID + "-" + subjectID + "-" + lecturerID + "-" + numericMark;
	            var signature = signMessage(privateKey, message);
				
				$.ajax({
					url: "${pageContext.request.contextPath }/verifySignatureServlet",
					  type: "POST",
					  //dataType: "JSON",
					  data: {
						  subjectID: subjectID,
						  subjectText: subjectText,
						  studentID: studentID,
						  studentName: studentName,
						  lecturerID: lecturerID,
						  score: numericMark,
						  message: message,
						  signature: signature
						  },
					  success: function(data) {
						  
						  if (data == 'falsified') {
							  alert("Ohh, The student's scores of subjects have been falsified!");
							  return;
						  }
						  alert("success!");
						  var idValue_score = "#" + studentID + "-" + subjectID;
						  $(idValue_score).text(numericMark);
						  $(idValue_score).css("color", "green");
						  
						  var idValue_operation = "#" + studentID + "-" + subjectID + "-o";
						  $(idValue_operation).text("MARKED!");
						  $(idValue_operation).css("color", "gray");
						  
					  },
					  error: function(xhr, status, error) {
						  alert("error");
					  }
				});
				
			} else {
				alert("Please give a valid score!");	
			}
		}
	}

</script>
</head>
<body>
<div class="fullName">  Hey, ${fullName} ! ${lecturerID}</div>
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
<div id="result">
	<table id="studentsList">
	    <thead>
	        <tr>
	            <th>studentID</th>
	            <th>fullName</th>
	            <th>Score</th>
	            <th>Operation</th>
	        </tr>
	    </thead>
	    <tbody>
	        <!-- Rows will be dynamically added here -->
	    </tbody>
	</table>
</div>



</body>
</html>