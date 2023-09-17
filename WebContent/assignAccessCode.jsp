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
</head>
<body>
<h2>Assign Access Code for Third Parties</h2>
<div id=ACcontainer>
	<form id="tpForm" method="post" action="${pageContext.request.contextPath }/assignAccessCode">
		<div class="tpSub">
			<div class="tpSubText">Third Party Email:</div>
			
			<input type="email" name="email"/>
		</div>
		
		<div class="tpSub">
			<div class="tpSubText">Expiration Date:</div>
			<select name="eDate">
				<option value=1>1 day</option>
				<option value=3>3 day</option>
				<option value=7>7 day</option>
			</select>
		</div>
		<input type="hidden" name="studentID" value="<%=request.getParameter("studentID") %>" />
		
		<input id="submit" type="submit" value="Submit"/>
	</form>
</div>
</body>
</html>