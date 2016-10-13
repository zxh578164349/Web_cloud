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

<script type="text/javascript">
 

jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});


jq(function() {
		var demo1 = jq("#subform").Validform({
			tiptype : 3,
			tipSweep : true,
			showAllError : true						
		});		
	})

</script>

</head>
<body>
  <form action="checkinput_print" method="post" id="subform" target="_blank">
	<table  id="tb_search">
	   <h2>資料輸入狀況</h2>
		<tr>
			<td>
			<span>
			 <s:if test="#session.factNo=='tw'">
					<select name="factNo" id="factNo" datatype="*">
						<option value="">請選擇廠別</option>	
						<option value="all">全部</option>					
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>
				</s:if> 
				<s:else>
					<select name="factNo" id="factNo">						
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>
				</s:else>
			</span>
			<span>
			 <input type="text" id="beginday" name="beginDate" onClick="WdatePicker({minDate:'{%y-1}-%m',maxDate:'#F{$dp.$D(\'endday\',{M:-1})||\'%y-{%M-1}\'}'})" readonly="readonly" datatype="*" class="Wdate"/>
			</span>至
			<span>
			 <input type="text" id="endday" name="endDate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'beginday\')}',maxDate:'%y-%M'})" readonly="readonly" datatype="*" class="Wdate"/>
			</span>
			<span>
			 <select name="temp" datatype="*">
			   <option value="">請選擇操作方式</option>
			   <option value="look">預覽</option>
			   <option value="down">下載</option>
			</select>
			</span>
			<input value="確定" type="submit" class="btn btn-primary"/>					
					 		 	
			</td>
		</tr>
	</table>
	<hr>
	</form>
</body>
</html>
