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
	<div>
		<form action="${pageContext.request.contextPath }/test", method="post">
			<input id="msg" type="hidden", name="msg" value="publish"/>
			<input id="sign" type="hidden", name="sign", value="testsign">
			<input id="publicKey" type="hidden", name="publicKey", value="">
			<input type="submit" id="btn" value="Publish" /> <input id="tra" />
        	<hr />
        	<div id="publicKey">12</div>
		</form>
        
    </div>
</body>
</html>