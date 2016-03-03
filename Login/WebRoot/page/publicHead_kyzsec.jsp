

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

<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">		
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery/loding/ui.loading.css" />
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>

<script type="text/javascript">
   
   jq(document).keyup(function(event){
     if(event.keyCode==13){
         submis();
     }
   }) 
   
   

</script>
<script type='text/javascript' src='/Login/dwr/interface/kytypejs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>
<body >
	<table  id="tb_search" >
		<tr>
			<td><s:if test="#session.factNo=='tw'">
					<select name="factNo" id="factNo">
						<option value="nothing">請選擇廠別</option>
						<s:iterator value="#session.facts" id="temp">
							<option value="${temp[0] }">${temp[1]}(${temp[0]})</option>								
						</s:iterator>
					</select>	
				</s:if> <s:else>
					<select name="factNo" id="factNo">
						<option value="<s:property value="#session.factNo"/>">
							<s:property value="#session.factName" />(<s:property value="#session.factNo"/>)
						</option>
					</select>	
				</s:else></td>			
			<td>組別代號</td>
			<td><input type="text" name="secNo" value="" id="secNo"/></td> 
			<td>
			<input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />
			</td>			
		</tr>
	</table>
</body>
</html>

