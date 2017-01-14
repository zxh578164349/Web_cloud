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
</head>
<body>
  <form  method="post" id="public_form">
   <div class="well">
	<table  id="tb_search">
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
			<td>進廠日期</td>
			<td><input type="text" id="year" name="yymm" 
				onClick="WdatePicker()"  class="Wdate"/></td>
			<td>驗收日期</td>
			<td><input type="text" id="year_s" name="yymm_s" 
				onClick="WdatePicker()"  class="Wdate"/></td>	
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
			 <input value="搜索" type="button" class="btn btn-primary" onclick="submis('public_form')" />
			 <input value="導出Excel" type="button" class="btn btn-primary" onclick="print('public_form')"/>			
			</td>
		</tr>
	</table>
	</div>
	</form>
</body>
</html>
