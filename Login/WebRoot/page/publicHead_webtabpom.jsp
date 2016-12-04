
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
  <form id="public_form" method="post">
	<table id="tb_search">
		<tr>
			<th>物性編號</th>
			<th>開始日期</th>
			<th>結束日期</th>
		</tr>
		<tr>	
			<td>
			   <input type="text" name="pomNo"/>
			</td>
			
			<td>
			  <input type="text" id="year" name="yymm" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>
			 </td>
			 <td> 
			  <input type="text" id="year" name="yymm2" onClick="WdatePicker({dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>
			  &nbsp;&nbsp;
			  <input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />
			  <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form')"/>				
			</td>
		</tr>
	</table>
	</form>
</body>
</html>
