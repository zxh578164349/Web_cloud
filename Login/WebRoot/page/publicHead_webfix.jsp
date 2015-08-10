<%@page
	import="org.springframework.context.support.ClassPathXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="dao.IKyFactDao"%>
<%@page import="services.impl.KyFactServicesImpl"%>
<%@page import="services.IKyFactServices"%>
<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@page import="entity.*"%>
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
  <div style="width:1190px">
  <form action="fix_toExcel" method="post" target="_blank">
	<table  border="0px">
		<tr>
			<td>廠別</td>
			<td><s:if test="#attr.factNo=='tw'">
			    <div id="uboxstyle">
					<select name="factNo" id="factNo">
						<option value="nothing">請選擇</option>						
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
				</s:else></td>
			<td>進廠日期</td>
			<td><input type="text" id="year" name="yymm" 
				onClick="WdatePicker()" readonly="readonly" class="Wdate"/></td>
			<td>驗收日期</td>
			<td><input type="text" id="year_s" name="yymm_s" 
				onClick="WdatePicker()" readonly="readonly" class="Wdate"/></td>	
			<td>
			<td>處分<td>
			<td>
                 <select name="lostmk" id="lostmk">
                     <option value="all">全部</option>
                     <option value="Y">已報廢</option>
                     <option value="N">未報廢</option>
                     <option value="N1">使用中</option>
                     <option value="N2">出售</option>
                     <option value="N3">閒置</option>
                 </select>
            </td>	
			<td>
			 <input value="搜索" type="button" id="addbtn" onclick="javascript:submis()" />
			 <input value="導出Excel" type="submit" id="search_forday" />			
			</td>
		</tr>
	</table>
	</form>
	</div>
</body>
</html>
