
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.alerts.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery_alert_dialogs/jquery.alerts.css" />	
<script type="text/javascript" src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>	
<link rel="stylesheet" type="text/css" href="jquery/loding/ui.loading.css" />	
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<script type="text/javascript">
 

jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});

</script>

</head>
<body>
  <div style="width:680px">
  <form id="public_form">
	<table  border="0px">
		<tr>
			<td>廠別</td>
			<td><s:if test="#session.factNo=='tw'">
			    <div id="uboxstyle">
					<select name="factNo" id="factNo">
						<option value="nothing">請選擇</option>						
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
					</div>
				</s:if> 
				<s:else>
				  <div id="uboxstyle">
					<select name="factNo" id="factNo">						
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>
					</div>
				</s:else></td>
			<td>年月</td>
			<td>
			開始<input type="text" id="begin_yymm" name="begin_yymm" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'end_yymm\')||\'%y-{%M-1}\'}',dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/><br/>
			結束<input type="text" id="end_yymm" name="end_yymm" onClick="WdatePicker({minDate:'#F{$dp.$D(\'begin_yymm\')}',maxDate:'%y-%M',dataFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/>
			</td>
			<td>
			 <input value="搜索" type="button" id="addbtn" onclick="submis('public_form')" />&nbsp;
			 <input value="導出" type="button" class="addbtn" onclick="print('public_form')"/>		
			</td>
		</tr>
	</table>
	</form>
	</div>
</body>
</html>
