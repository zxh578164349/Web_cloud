<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<jsp:useBean id="cookie" class="util.Cookie" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date = formatter.format(currentTime); //将日期时间格式化
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!-- General meta information -->
<title>copyright</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="robots" content="index, follow" />
<meta charset="utf-8" />

<!-- Load stylesheets -->
<link type="text/css" rel="stylesheet" href="css/copyright.css" media="screen" />		
<!-- // Load stylesheets -->

</head>
<body >	
	<div class="copyright" >Copyright © 2014-<%=str_date%>,東莞加元鞋材制品有限公司,All Rights Reserved</div>
</body>
</html>