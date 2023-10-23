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
	<h1 id="loginTitle">Please Login Here</h1>
	<div id="loginContainer">
		<form id="loginForm" action="" method="post">
		
			<div class="subConta">
				<div class="inputText">Username: </div>
				<div style="float: left;">
					<input id="username" name="username" type="text" placeholder="input your username here" />
				</div>
			</div>
			
			<div class="subConta">
				<div class="inputText">Password: </div>
				<div style="float: left;">
					<input id="password" name="password" type="password" placeholder="input your password here"/>
				</div>
			</div>
			
			<div id="btnDiv">
				
				<button id="login">Login</button>
			</div>
			
			
			<input id="type" type="hidden" name="type" value="<%=request.getParameter("type")%>"/>
			
		</form>
        
    </div>
</body>
</html>