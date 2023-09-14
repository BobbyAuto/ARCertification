<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Academic Records Certification System</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/all.css">

</head>
<body>
	<h1 id="indexTitle">Academic Records Certification System</h1>
	<div id="indexContainer">

		<div id="indexSubContainer">
			<div>
				<a href="login.jsp?type=student">I'm a Student</a>
			</div>
			<div>
				<a href="login.jsp?type=lecturer">I'm a Lecturer</a>
			</div>
			<div>
				<a href="login.jsp?type=empoloyer">I'm a Empoloyer</a>
			</div>
		</div>

	</div>
</body>
</html>