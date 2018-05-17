
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
 <form id="public_form">
 
 
	<table  id="tb_search" class="table">
	    <tr>
	      <td>郵件類型</td><td>用戶</td><td>Email</td><td>EmailOrCc</td><td>是否發送郵件</td>
	    </tr>
		<tr>
			<td>
				<select name="eid" id="sel_emailtypes"></select>											
			</td>	
			<td>
			<input type="text" name="uname"/>
			</td>
			<td><input type="text" name="email"/></td>	
			<td>
			  Email<input type="radio" value="0" name="emailOrcc">&nbsp;&nbsp;
			  Cc<input type="radio" value="1" name="emailOrcc">&nbsp;&nbsp;
			</td>
			<td>
			  是<input type="radio" value="Y" name="emailMk">&nbsp;&nbsp;
			  否<input type="radio" value="N" name="emailMk">&nbsp;&nbsp;
			</td>	
			<td>
			 <input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />		
			</td>
		</tr>
	</table>	
 </form>	
</body>
</html>
