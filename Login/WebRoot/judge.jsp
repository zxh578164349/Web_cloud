<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>


<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'judge.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="shortcut icon" href="images/icon/web_ico.ico" />

<!--[if !IE]><!-->
<link type="text/css" rel="stylesheet" href="loginpage/css/login_style_chrome.css" media="screen" />
 <!--<![endif]-->
<!--[if lt IE 9]>
<link type="text/css" rel="stylesheet" href="loginpage/css/login_style_ie.css" media="screen"> 
<![endif]-->

</head>

<body>
<div id="bodyid" style="display:none"></div>
<jsp:include page="copyright_login.jsp"/>

<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="loginpage/jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="loginpage/jquery/layer/layer.min.js"></script>
<script type="text/javascript">
	var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load("請稍等...");
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
	jq(function (){
		var agent = navigator.userAgent.toLowerCase();
		if (agent.indexOf("msie") > 0) {			
			//window.location.href = "/Login/webfact_findAllfact";
			jq("#bodyid").load("/Login/webfact_findAllfact");						
		}else {			
			//window.location.href = "/Login/webfact_findAllfact2";
			jq("#bodyid").load("/Login/webfact_findAllfact2");
		}
		jq("#bodyid").fadeIn(1500);
		//jq("#bodyid").slideDown(300);
		
	});
	
</script> 
</body >
</html>
