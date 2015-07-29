<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Test1.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="jquery/jquery-1.7.2.js"></script>
<script type="text/javascript">
	
</script>

</head>
<body>
	<form action="sendmail" method="post">
		<input type="hidden" value="Kyinfo.hbr@yyin.yydg.com.cn"
			name="mailArray">
		<!-- <input type="hidden" value="Kyinfo.David@yyin.yydg.com.cn" name="mailArray" > -->
		<input type="hidden" value="你好" name="content"> <input
			type="hidden" value="測試" name="subject"> <input type="submit"
			value="請點擊">
			<hr>
	</form>
</body>
</html>
