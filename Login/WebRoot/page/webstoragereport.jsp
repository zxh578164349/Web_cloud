<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'ypre_null.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript">

jq(function(){
    jq("#subform").Validform({
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true
    });            
});
</script>
</head>

<body>
<br/>
	<form action="weballobjb_printstorage" method="post" id="subform" target="_blank">
		<table id="tb_search">
			<tr>
				<td>
				<span>
				 <input type="text" name="yymm" onClick="WdatePicker({dateFmt:'yyyy'})"
					readonly="readonly" class="Wdate" datatype="*" />
				</span>				
				 <input type="submit" value="下載"  class="btn btn-primary"/>&nbsp;&nbsp;				 
				</td>			    
				
			</tr>
		</table>
	</form>				
	<hr>
</body>
</html>
