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
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="page/jquerys/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="page/jquerys/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquerys/DatePicker/my2_WdatePicker.js"></script>
<script type="text/javascript">
 

$(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});


$(function() {
		var j = jQuery.noConflict();
		var demo1 = j("#subform").Validform({
			btnSubmit : "#btn",
			tiptype : 3,
			tipSweep : true,
			showAllError : true						
		});		
	})

</script>

</head>
<body>
  <div style="width:920px">
  <form action="checkinput_print" method="post" id="subform" target="_blank">
	<table  border="0px">
	   <caption>資料輸入狀況</caption>
		<tr>
			<td>廠別</td>
			<td><s:if test="#attr.factNo=='tw'">
			    <div id="uboxstyle">
					<select name="factNo" id="factNo" datatype="*">
						<option value="">請選擇</option>						
						<s:iterator value="#attr.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					</div>
				</s:if> 
				<s:else>
				  <div id="uboxstyle">
					<select name="factNo" id="factNo">						
						<option value="<s:property value="#attr.factNo"/>">
							<s:property value="#attr.factName" />(<s:property value="#attr.factNo"/>)
						</option>
					</select>
					</div>
				</s:else>				
				</td>
			<td>年月</td>
			<td>			
			<span>開始<input type="text" id="beginday" name="beginDate" onClick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'endday\',{M:-1})||\'%y-{%M-1}\'}'})" readonly="readonly" datatype="*"/></span><br>
			<span>結束<input type="text" id="endday" name="endDate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'beginday\')}',maxDate:'%y-%M'})" readonly="readonly" datatype="*"/></span>
			</td>
			<td>操作方式</td>
			<td>
			<select name="temp" datatype="*">
			   <option value="">請選擇</option>
			   <option value="look">預覽</option>
			   <option value="down">下載</option>
			</select></td>
			<td>
			 <input value="確定" type="button" id="btn"/>			 	
			</td>
		</tr>
	</table>
	<hr>
	</form>
	</div>
</body>
</html>
