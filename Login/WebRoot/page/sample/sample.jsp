<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<title>My JSP 'success.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<img src="images/sample.jpg"/>
	<p style="color:red"><br/>
	   <b>(1)格式以ERP系統導出爲准,導出之後,在"廠別"右邊新插入一列"廠別狀態"列，如圖中紅色框圈出部分，並且添加相對應的廠別狀態</b><br/>
	   <b>(2)廠別必須包含廠別代號  例如:加元一廠(631)</b><br/>
	   <b>(3)除了添加"廠別狀態"列,修改數據,其它都不宜修改刪除</b><br/>
	   <b>(4)經過ERP導出的Excel文檔,要使用WPS或者Office打開幷保存一次,方可導入到WEB系統</b>
	</p>
</body>
</html>
