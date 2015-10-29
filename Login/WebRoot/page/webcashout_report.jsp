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
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

$(function(){
    $("#subform").Validform({
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true
    })
})
</script>
</head>

<body>
	<form action="webcashout_report" method="post" id="subform" target="_blank">
		<table>
		  <caption>生產與請款達成狀況統計分析表</caption>
			<tr>
			    <td>日期</td>
				<td><input type="text" name="yymm" onClick="WdatePicker({dateFmt:'yyyyMM'})"
					readonly="readonly" class="Wdate" datatype="*" id="iyymm" />
				</td>
				<td>廠別</td>
			    <td><s:if test="#attr.factNo=='tw'">
					<select name="factNo" id="factNo">
						<option value="all">全部</option>						
						<s:iterator value="#attr.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
				</s:if> 
				<s:else>
					<select name="factNo" id="factNo">						
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />(<s:property value="#attr.factNo"/>)
						</option>
					</select>
				</s:else>
				</td>
				<td>
				    <input type="submit" value="下載"  />&nbsp;&nbsp;
					<input type="button" value="返回" onclick="javascript:location.href='webestpro_findPageBean'" />					
				</td>
			</tr>
		</table>
	</form>
	<hr>





</body>
</html>
