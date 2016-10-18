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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<link rel="stylesheet" type="text/css" href="css/form.css" />	
<script type="text/javascript">

   jq(function(){
	    jq("#subform").Validform({
				tiptype : 3,
				showAllError : true,
				ignoreHidden : true,
				tipSweep : true
	    })
	}) 
</script>
</head>

<body>
<h2 id="h2_title">預計生產與請款匯總</h2>
<br/>
	<form action="webestpro_print" method="post" id="subform" target="_blank">
		<table id="tb_search">
			<tr>
				<td>
				<span>
				 <input type="text" name="yymm" onClick="WdatePicker()"
					readonly="readonly" class="Wdate" datatype="*" id="iyymm" />
				</span>
					<input type="hidden" name="lookordown" id="lookordown"/>
					<input type="submit" value="預覽"  class="btn btn-primary"/>&nbsp;&nbsp;
				    <input type="submit" value="下載"  class="btn btn-primary"/>&nbsp;&nbsp;
					<input type="button" value="返回" onclick="loadUrl('webestpro_findPageBean')" class="btn btn-primary"/>
				</td>				
			</tr>
		</table>
	</form>
	<hr>





</body>
</html>
