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
<link type="text/css" rel="stylesheet" href="../css/copyright.css" media="screen" />		
<!-- // Load stylesheets -->

</head>
<body >	
	<div class="copyright" >Copyright © 2014-<%=str_date%>,加久企業股份有限公司,All Rights Reserved
	<!-- <script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1259940248'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1259940248%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));</script> -->
	</div>	
</body>
</html>