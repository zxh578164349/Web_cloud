
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
	<form id="subform">
		<table id="tb_search" class="table table-condensed">
			<tr>
				<th>廠別</th>
				<th>帳號</th>
				<th>用戶類型</th>
			</tr>
			<tr>
				<td><s:if test="#session.factNo=='tw'">
						<select name="factNo" id="factNo">
							<option value="">請選擇廠別</option>
							<option value="tw">TW</option>
							<s:iterator value="#session.facts" id="temp">
								<option value="${temp[0]}">${temp[1]}(${temp[0]})</option>
							</s:iterator>
						</select>
					</s:if> <s:else>
						<select name="factNo" id="factNo">
							<option value="<s:property value="#session.factNo"/>">
								<s:property value="#session.factName" />
								(
								<s:property value="#session.factNo" />
								)
							</option>
						</select>
					</s:else></td>
				<td><input type="text" id="conditions" name="conditions" />
				</td>
				<td>使用者<input type="radio" value="0" name="userType" />&nbsp;&nbsp;訪客<input type="radio" value="1" name="userType" />&nbsp;
				<input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis('subform')" />
				</td>
				
			</tr>
		</table>
	</form>
</body>
</html>
