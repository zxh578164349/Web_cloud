<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path=request.getContextPath();
	String basePath=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%
	java.text.SimpleDateFormat formatter=new java.text.SimpleDateFormat("yyyyMMdd-hh");
	java.util.Date currentTime=new java.util.Date();//得到当前系统时间
	String str_date=formatter.format(currentTime); //将日期时间格式化
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>	
	<table class="table table-condensed table-bordered">
		<tr>
			<th>配方階段</th>
			<th>類別</th>
			<th>原料名稱</th>
			<th>PHR</th>
			<th>重量(KG)</th>
			<th>備註</th>
		<tr>
			<s:iterator value="formula.webFormulaItemses" status="x">
			<tr>
				<td><s:property value='sectionNo' />
				</td>
				<td><s:property value="fk_weberp_pf.selfchar1Name" />
				</td>
				<td ><s:property value="fk_weberp_pf.namec1" />&nbsp;&nbsp;&nbsp;<s:property value="fk_weberp_pf.namec2" /></td>
				<td><s:property value='phrVal' /></td>
				<td><s:property value='weightVal' /></td>
				<td><s:property value='remark' /></td>
			</tr>	
			</s:iterator>
	</table>
</body>
</html>
