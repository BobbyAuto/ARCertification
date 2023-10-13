<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/js/jquery-3.7.0.js"></script>

<script src="${pageContext.request.contextPath }/js/jsrsasign-all-min.js"></script>

<script type="text/javascript">


	function signature(msg, prvKeyPem){
        var sig = new KJUR.crypto.Signature({"alg": "SHA1withRSA"});
        sig.init(prvKeyPem);
        sig.updateString(msg)
        var sigValueHex = sig.sign()

        var rstr = hextorstr(sigValueHex);
        base64str = btoa(rstr);
        return base64str;
	}
    function onClick() {
            /* prvpem = "-----BEGIN PRIVATE KEY-----\r\n \
            MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJEazjBHiwUKLiPr\r\n \
            0voCnYdtOpjwZWOU7mg9CvNAo/B+hT/Jju9rZ40EnSQ+DTJPxfavUO+nKqn30oVH\r\n \
            jED/tQtKjvF4Xh1ZFbv4c6O4iE1EZF/CNXDl6Gb4ixdVI5vGr4zckzRI11UJO1PN\r\n \
            eYLM7RiSOxeVavt/GdCLwwo2tAjfAgMBAAECgYEAhbPAV2Ykp8EooLq0DOAKDPZN\r\n \
            rwybL7XUM0JD674dEQVTfGIZjWxW3u5oaTOv7zLoQxRprFlJC5IpbdtuVZoIU3pa\r\n \
            o7TJNJ8jQ6/0cKeKZlUH8Tj58nfbCEsi9XfP6zBOZzz5q+tfzT1d6wJYC/VNvyPY\r\n \
            SBINj4Ez41kG/zMN7CECQQDMKhoN+cvlVD5B/PJQwY3JsfoNETY96NBL5WAobtOX\r\n \
            kMccgFR+Yz8VNLjE+xXcIilYK4QSyQx+d/49CSAtLyjRAkEAtfIMf6PFSCAMxriN\r\n \
            cpCxYbe1RYPjN4Bogv6yRDdAWdiIdfZW+Zd4Ag/AoXCd5ihSFdaZniOLV1qyMNZo\r\n \
            9C2CrwJATLHIJdXflV+HH6zQrit3gvwbrcr4cFtD3C7nZ2jyjW4yje9bTjskGGg8\r\n \
            vKqBtLcUhu2Z6KCj0+JpnC4Qa0ja8QJBAIn+vTAJ3FUpycbmrpFX4NFGbjv9HdY4\r\n \
            lAvWdQp8BViffFBLuAextBmMLQPE0F2B81AQ5Y4lU0e7yC52UD/yTI0CQQCP8rlQ\r\n \
            pWB+u9r/jB9zE1BcdA7X0EJQOqZu1/GvI9HFPZ52UNiOC796r50DexqG23e2/v28\r\n \
            StXAzY7tlMcSC7Lz\r\n \
            -----END PRIVATE KEY-----"; */
            
            /* privateKey = "-----BEGIN PRIVATE KEY-----
            	MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJade1LTfwbeONcE
            	kQOD59xnJR2XJw7OOrUmjHnc64uKBXHUqzJ797CuWyPHoTLNNoBEt51nlb8oNS67
            	NRIaGU5Jh1pML6qHpqs/ydu5OAjvLcaQAKU0RdJtmvlMxRu75fdKS9baQ8d55ReI
            	ACO9RzYwcJDwQlzBFQnJODDsjzqVAgMBAAECgYAvvDvQ1W1GHPpA05auWYkL5rjx
            	tu1PGPesYEpXeThCIn11RWT4/CdVnP0Pyv6BHl8uqhjTz3e7fR/V7sl0Mkdgdt1i
            	fbi9xjLqsHV89F0WVIxFsPA4XgBUtQEv3lMrVBVOecBiCtJ1m3wBevvLyZDU/9k0
            	8ZSaJyF051V/uQv25QJBANFnfNKVs9xjkyLHEtiIaLJswy6IZKaoPFP0A7VZBqiE
            	ENV6b3hRi4DYvFf3ynTC693l/E1mKHOOr+/gpjWKRlMCQQC4ISFVn0tK1ghh1K8+
            	Z2TDECLOe/7CoOrqdieGjIm6QN//U6GKicaRF7uE3H/q0+btHgzhWlci34uxgMSS
            	0A53AkAOa4Lz+W0eUxkRvwW0NYiIKOcz8B6GP3WQDnCm709/EUEbgkfk0DC8GbC2
            	mhCuA7I8YJcEOXG1q3DAIwcvDquTAkB30Oc2JkACZKBzuMiIXROOczOTFdDDqZ70
            	DwrNXSpKTujz5uHZmqnhxIW7gRikIFvGtKVBB15oTPtD+DzyI/0lAkBMSXej/GjV
            	rbRCNT+6XRo68AKQ+E/QbUxyeOkh1lJELzrvvpfvHt11GngjTJ7GeB3BbaWQFNuY
            	XOJBvI39EGGp
            	-----END PRIVATE KEY-----"; */
            	
            /* prikey = "-----BEGIN PRIVATE KEY-----\r\n \
            	MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPWBvEcQLTe3p8Dw\r\n \
            	wrwyedGrrqH8jo8Ofs9WRN3oAAwEImuZLOhKWYU0LsSrfhWOARg4mLH2Iks4HSAR\r\n \
            	FWul0nDvMNn4kTs3GgyI4cI/KNY1fe9vSfjGMpQorHKalZygiFSMX5AfrQWSJAvQ\r\n \
            	lQRYsTIhkqBjXwXuIB97SglyDKT/AgMBAAECgYEAxu2TkIVUDlSa8iRjtji0KqeQ\r\n \
            	/iJ1pQcay4fcj25AM3xaI5NfJu2vsdZBmwxWQsLosiaR24lT6l0ShHwzPiGD7HYh\r\n \
            	mEASFRLmLuDZdB1WLOGqCLmHf9knhz6dKdFvdeJSBrFzI+ju5TXD0eqtki+Uxb0m\r\n \
            	fz07pL3YD95pXK6LPhECQQD+d47DC7iptH9xZ/xN9E41j127qNXWS1WtvZo3L1U/\r\n \
            	b9Z4DyQBPonhIv+TqT848ldaAwRNZAMkyXc32wHhYkipAkEA9vxb9BFRYA95+hxH\r\n \
            	RXeHqvmaJSE21ZWqCqnsu+xDKESuGOUNIkePJl8jJORDwHKgUpjb5n2989SFSACz\r\n \
            	JyrBZwJAOyeJ7XeVFwZZtgBJW8nWQIcycASAqU9b/IaeCgQb2iJihghnA08JeGfN\r\n \
            	aAMpKlDT053t6xu7y1p2N2rXFhDl+QJAFUoBXxNTEVWkLPPdDNbVwd4L9GFdiaGB\r\n \
            	67Nj7Xra+xCYdRrhO/AxHZybueVXFbKLsTLt2XtDMU5DQsaUqq6bxwJBAKyIbEv6\r\n \
            	8/TjP3oT/D2BnzCOREGk2jlomGlcjjEHdZn/z520H0irvGxIO6D9exSMn0dAH1sO\r\n \
            	4GYMFA+a/QlOwaI=\r\n \
            	-----END PRIVATE KEY-----"; */
            	
            	
            	prikey = "-----BEGIN PRIVATE KEY-----\r\n \
            		MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAIpuDncKBPq5k48F\r\n \
            		oJTMQmuGJgQiUngwPLetT1BpMVaxqBLm9x3tQe6i2uY7ZQQZ3DIH1PG3AEbT2qJG\r\n \
            		DJSDey0B3mhvIj1rPQxV+h5MPffyQrBvpSjSSsF+fyAVWcSFilAVdGO60cXON1mW\r\n \
            		64+P4eT46MufA/C7HdHPigTX8Sr9AgMBAAECgYEAgA6DjxdTt+UIXdwkysGSCAnX\r\n \
            		DSf7BngJExleL9K/86TxCFtk6uhaf7ljqmvzbLHHgvi/wsM5nEvLCyal6JUg0gYW\r\n \
            		n8Nm79CSk+y89GKcEAwyIF7eWRHNWboNkaOXaN/DCszYAf1xND2FDNqyAA1m1gJm\r\n \
            		TZIWkmVmiMgYI/wnQbkCQQDgmRi+qRhwP9FP66TU0fn1r8oh8gymvTfvXdVNMyUp\r\n \
            		aikzDgroAEs1PVl7/TKeXLE/f19XGDv2QyubO4BJy5TfAkEAncjLqkNVtiAMUOcf\r\n \
            		mkx4x2+N0ZgV6V2r+/3u6xNsrv+4SUasgnCn4LaY9vwRBf8JCXIjeRi+EThmHGLN\r\n \
            		iaG/owJBAMpQvpyIv83lfswKTbmzyAFLr74yRrkwO6GwmcLn7nLHAYPPA34HPbgg\r\n \
            		omnxI9bJUpULTLusxd7IlRBJYf+XlmsCQBYz2STeUMNOAy2nODU4KY62zdf+dKbf\r\n \
            		/YF6HBKMKzD83uvAvCmmvJjMWP34PlT/rD/eBcvWhXha3VOFO7LwRc0CQG70qkZg\r\n \
            		mk6Q1hnLjZibybnd0WKodK8bI20wzNtClaB3jDgoX0UIqfywjKi2DkiOJNj6qUTF\r\n \
            		apkZP8hE5EqOogc=\r\n \
            		-----END PRIVATE KEY-----";
            	
            alert(prikey.length);
            
            
            msg = "hello";
            const signedResult = signature(msg, prikey);
            console.log(signedResult);
            $("#sign").text(signedResult); 
        }
    
    function generateKey(){
        var kp = KEYUTIL.generateKeypair("RSA", 1024);
        // ECC
        // var kp = rs.KEYUTIL.generateKeypair("EC", "secp256r1");
        var prv = kp.prvKeyObj;
        var pub = kp.pubKeyObj;
        var prvpem = KEYUTIL.getPEM(prv, "PKCS8PRV");
        var pubpem = KEYUTIL.getPEM(pub, "PKCS8PUB");
        console.log("prvpem:");
        console.log(prvpem);
        console.log('pubpem:');
        console.log(pubpem);
        alert(prvpem.length);
        
         $("#prikey").text(prvpem); 
         $("#pubkey").text(pubpem); 
         alert($("#prikey").text().length);
     /*    alert(document.getElementID("prikey").innerText); */
    }
    </script>
</head>
<body>
    <button onclick="onClick()">sign</button>
    
    <button onclick="generateKey()">generateKeys</button>
    
    <P id="prikey">123</P> <br />
    <P id="pubkey">123</P> <br />
    
    <p id = "sign"></p>
    
</body>
</html>