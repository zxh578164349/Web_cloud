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

<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="loginpage/jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="loginpage/jquery/layer/layer.min.js"></script>
<script type="text/javascript">
	var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load("正在登錄,請稍等...");
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
	jq(function (){
		/*jQuery.fn.center = function () {
		      this.css("position","absolute");
		      this.css("top", ( jq(window).height() - this.height() ) / 2+jq(window).scrollTop() + "px");
		      this.css("left", ( jq(window).width() - this.width() ) / 2+jq(window).scrollLeft() + "px");
		      return this;
		  }
        jq("#bodyid").center();*/
        
        
		var agent = navigator.userAgent.toLowerCase();
		if (agent.indexOf("msie") > 0) {			
			//window.location.href = "/Login/webfact_findAllfact";
			jq("#bodyid").load("/Login/webfact_findAllfact");
			
		}else {			
			//window.location.href = "/Login/webfact_findAllfact2";
			jq("#bodyid").load("/Login/webfact_findAllfact2");
		}
		
		
	});
	
</script> 
</head>

<body>
<!--  <div id="bodyid" style="width:200px;height:200px;background:red;"></div>-->
<div id="bodyid"></div>
<jsp:include page="copyright_login.jsp"/>
</body >
</html>
