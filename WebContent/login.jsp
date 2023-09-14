<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/all.css">
<script src="${pageContext.request.contextPath }/js/jquery-3.7.0.js"></script>

<script type="text/javascript">
$(document).ready(function(){
            $("#login").click(function() {
            	
            	var username = $("#username").val();
            	var password = $("#password").val();
            	
            	var loginForm = $("#loginForm");
            	loginForm.attr("action", "${pageContext.request.contextPath }/loginDispatcher");
            	loginForm.submit();
            })
        });
    </script>

</head>



<body>
	<h1 style="width:100%; margin-top:200px; text-align:center;">Please Login Here</h1>
	<div style="width:100%; height:auto; display: flex; justify-content: center; align-items: center;">
		<form id="loginForm" action="" method="post">
			<div style="width:100%; height: 35px; ">
				<div style="width:80px; height:35px; text-align: left; float: left; line-height: 35px; font-size:16px;">Username: </div>
				<div style="float: left;">
					<input id="username" name="username" type="text" placeholder="input your username here" style="width: 250px; height:35px; border: 1px solid #D8D8D8"/>
				</div>
			</div>
			<div style="width:100%; height: 35px;  margin-top:18px;">
				<div style="width:80px; height:35px; text-align: left; float: left; line-height: 35px; font-size:16px;">Password: </div>
				<div style="float: left;">
					<input id="password" name="password" type="password" placeholder="input your password here" style="width: 250px; height:35px; border: 1px solid #D8D8D8"/>
				</div>
			</div>
			
			<div style="width:330px; height: 35px;  margin-top:28px; display: flex; justify-content: right; align-items: right;">
				
				<button id="login" style="width: 100px; height:35px; font-size:15px;">Login</button>
			</div>
			
			<!-- hidden field to store the lecturer's signature -->
			<input id="type" type="hidden" name="type" value="<%=request.getParameter("type")%>"/>
			
		</form>
        
    </div>
</body>
</html>