<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'viewcost.jsp' starting page</title>
    
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
    <table>
      <tr><td>廠別</td><td>廠別狀態</td><td>日期</td><td>actlost</td><td>hole</td></tr>
      <s:iterator value="#session.viewlist">
      <tr><td><s:property value="id.factNo"/></td><td><s:property value="id.factCode"/></td><td><s:date name="id.yymm" format="yyyyMM"/></td>
      <td><s:property value="actlost"/></td><td><s:property value="hole"/></td></tr>
      </s:iterator>
    </table>
  </body>
</html>
