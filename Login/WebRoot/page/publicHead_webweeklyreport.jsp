
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
 
 
	<table  id="tb_search">
		<tr>
			<td>
				<select name=bid id="dwrWebbrank"></select>											
			</td>	
			<td>
			<input type="text" name="sdate" class="Wdate" onclick="WdatePicker({disabledDays:[0,2,3,4,5,6],dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})"/>
			</td>		
			<td>
			 <input value="搜索" type="button" class="btn btn-primary" onclick="javascript:submis()" />		
			</td>
		</tr>
	</table>	
 </form>	
</body>
</html>
