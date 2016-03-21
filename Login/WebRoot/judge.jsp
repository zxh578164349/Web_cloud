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
</head>
<!--<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script> -->
<script src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.min.js"></script> 
<script>window.jQuery || document.write('<script src="/jquery/jquery-1.9.1.min.js"><\/script>');</script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>


<body onload="loadpage()">
	<script type="text/javascript">
	function loadpage(){
		var agent = navigator.userAgent.toLowerCase();
		layer.load("正在加載,請稍等...");
		if (agent.indexOf("msie") > 0) {			
			window.location.href = "/Login/webfact_findAllfact";
		}else {			
			window.location.href = "/Login/webfact_findAllfact2";
		}	
	}
	

</script>
<jsp:include page="copyright_login.jsp"/>
</body >
</html>
