
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML>
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
  <div class="well">
	<table  id="tb_search">
		<tr>
			<td><s:if test="#session.factNo=='tw'">
			    <div id="uboxstyle">
					<select name="factNo" id="factNo">
						<option value="nothing">請選擇廠別</option>						
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
			<td><input type="text" id="year" name="yymm" 
				onClick="WdatePicker({dateFmt:'yyyyMM'})" readonly="readonly" class="Wdate"/></td>
			<td>
			 <input value="搜索" type="submit" class="btn btn-primary" onclick="javascript:submis()" />	
			</td>
		</tr>
	</table>
	</div>
</body>
</html>
