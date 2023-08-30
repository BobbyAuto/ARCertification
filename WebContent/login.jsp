<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Here</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.7.0.js"></script>
<script src="${pageContext.request.contextPath }/js/jsencrypt-3.2.1.js"></script>
<script src="${pageContext.request.contextPath }/js/crypto-js.js"></script>
<script src="${pageContext.request.contextPath }/js/Tools.js"></script>

<script type="text/javascript">
$(document).ready(function(){
            var keys = generateKeyPair();
            var publicKey = keys.publicKey;
            var privateKey = keys.privateKey;
            
            var publicKeyBase64 = publicKey
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replace(/\s/g, '');
            
            $("#publicKey").val(publicKeyBase64)
            //alert($("#publicKey").val());

            $("#btn").click(function () {
            	
            	var message =  $("#msg").val();
                var signature = signMessage(privateKey, message);
                $("#sign").val(signature);
                
                var isvalid = verifySignature(publicKey, message, signature);
                alert(isvalid)

            });
        });
    </script>

</head>



<body>
	<h1 style="width:100%; margin-top:200px; text-align:center;">Please Login Here</h1>
	<div style="width:100%; height:auto; display: flex; justify-content: center; align-items: center;">
		<form action="" method="post">
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
				
				<button style="width: 100px; height:35px; font-size:15px;">Login</button>
			</div>
		</form>
	
		<%-- <form action="${pageContext.request.contextPath }/test" method="post">
			<input id="msg" type="hidden" name="msg" value="publish"/>
			<input id="sign" type="hidden" name="sign", value="testsign">
			<input id="publicKey" type="hidden" name="publicKey", value="">
			<input type="submit" id="btn" value="Publish" /> <input id="tra" />
        	<hr />
        	<div id="publicKey">12</div>
		</form> --%>
        
    </div>
</body>
</html>