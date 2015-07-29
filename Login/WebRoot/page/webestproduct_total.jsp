<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>My JSP 'ypre_null.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="page/jquerys/Validform_v5.3.2_min.js"></script>
<script type="text/javascript"
	src="jquerys/DatePicker/my2_WdatePicker.js"></script>
<script type="text/javascript">


	function look() {
	   var temp=document.getElementById("lookordown").value="look";
		var iyymm = document.getElementById("iyymm");
		var subform = document.getElementById("subform");
		var syymm = document.getElementById("syymm");
		if (iyymm.value == "") {
			syymm.innerHTML = "<font color='red'>(日期不能為空)</font>";
			return false;
		} else {
			subform.submit();
		}
	}
   function down() {
	   var temp=document.getElementById("lookordown").value="down";
		var iyymm = document.getElementById("iyymm");
		var subform = document.getElementById("subform");
		var syymm = document.getElementById("syymm");
		if (iyymm.value == "") {
			syymm.innerHTML = "<font color='red'>(日期不能為空)</font>";
			return false;
		} else {
			subform.submit();
		}
	}
</script>
</head>

<body>
	<form action="webestpro_print" method="post" id="subform" target="_blank">
		<table>
		  <caption>預計生產與請款匯總</caption>
			<tr>
				<td><input type="text" name="yymm" onClick="WdatePicker()"
					readonly="readonly" class="Wdate" datatype="*" id="iyymm" /><span
					id="syymm"></span>
					<input type="hidden" name="lookordown" id="lookordown"/>
				</td>
				<td>
				    <input type="button" value="預覽" onclick="look()"/>&nbsp;&nbsp;
				    <input type="button" value="下載" onclick="down()" />&nbsp;&nbsp;
					<input type="button" value="返回"
					onclick="javascript:location.href='webestpro_findPageBean'" />
				</td>
			</tr>
		</table>
	</form>
	<hr>





</body>
</html>
