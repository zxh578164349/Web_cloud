<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!Dtional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'none.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<style type="text/css">
  #box{  
      text-align:center;
      padding-top:50px
  }
 
</style>
<body>	  
	<div id="box">
	   <div id="child">
	               此功能關閉 轉移至PIS登打！
	   </div>
	</div>  	
</body>
<script>
alert("此功能關閉 轉移至PIS登打!");
//window.opener=null;
//window.open("","_self");
//window.close();
</script>
</html>
