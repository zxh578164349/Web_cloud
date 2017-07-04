<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML　"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">	
</head>
<body >
	<table  id="tb_search">
		<tr>
			<td>科目代號</td>
			<td><input type="text" name="acctNo" id="acctno"></td>			
			<td>科目名稱</td>
			<td><input type="text" name="acctName" value="" id="acctname"/></td> 
			<td><!-- <input type="image" onclick="javascript:submis();"  src="images/search002.gif"/> -->
			<input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />
			</td>			
		</tr>
	</table>
</body>
</html>

