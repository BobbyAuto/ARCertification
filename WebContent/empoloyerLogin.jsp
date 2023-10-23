<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assign Access Code</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/all.css">
<style type="text/css">
html, body {
    height: 100%;
    margin: 0;
    padding: 0;
    display: flex; justify-content: center; align-items: center;
}
h2 {
	text-align: center;
	position: absolute;
	top: 40px; 
}
</style>
<script type="text/javascript">
<%
	if (request.getAttribute("result") != null) {
		%>
		alert("Access code allocation successful.");
		<%
	}
%>

</script>
</head>
<body>
<h2>Access student's academic records.</h2>
<div id=ACcontainer>
	<form id="tpForm" method="post" action="${pageContext.request.contextPath }/loginDispatcher">
		<div class="tpSub">
			<div class="tpSubText">Third Party Email:</div>
			<input type="email" name="email"/>
		</div>
		
		<div class="tpSub">
			<div class="tpSubText">Student Name:</div>
			<input type="text" name="studentName"/>
		</div>
		
		<div class="tpSub">
			<div class="tpSubText">Student ID:</div>
			<input type="text" name="studentId"/>
		</div>
		
		<div class="tpSub">
			<div class="tpSubText">Access Code:</div>
			<input type="text" name="accessCode"/>
		</div>
		
		<input id="type" type="hidden" name="type" value="<%=request.getParameter("type")%>"/>
		<input id="submit" type="submit" value="Submit"/>
	</form>
</div>
</body>
</html>