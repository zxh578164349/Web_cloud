
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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<LINK href="css/list.css" type="text/css" rel="stylesheet">
</head>
<body>
   <form id="search_form">
	<table  id="tb_search">
		<tr>
			<td><s:if test="#session.factNo=='tw'">
					<select name="factNo" id="factNo">
						<option value="">請選擇廠別</option>						
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
				</s:else></td>
			<td>			
				單號<input type="text" name="billNo"/>
			</td>
			<td>
			 <input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />	
			</td>
		</tr>
	</table>
	</form>	
</body>
</html>
