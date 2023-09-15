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
<p id="headContainer">
	<p id="sdLeft">Assessment Details</p>
	<p id="sdMiddle">Verified.</p>
	<p id="sdRight">${student.getFullName() }</p>
</p>
<span>${verifyStatus}</span>
<span>${markSheetsList.size() }</span>
</body>
</html>