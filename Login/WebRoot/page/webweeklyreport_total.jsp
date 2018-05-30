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
<title>My JSP 'publicHead.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">	
<link rel="stylesheet" type="text/css" href="css/form.css" />

</head>
<body>
<br/>
  <form action="webweekly_print" method="post" id="subform" target="_blank">
	<table  id="tb_search">
		<tr>
			<td>			
			<span>
			 <input type="text" name="sdate" class="Wdate" onclick="WdatePicker({disabledDays:[0,2,3,4,5,6],dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})" datatype="*"/>
			</span>			
			<input value="導出" type="submit" id="btn" class="btn btn-primary"/>										 		 	
			</td>
		</tr>
	</table>
	<hr>
	</form>
</body>
<script type="text/javascript">
jq(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});


jq(function() {
		var demo1 = jq("#subform").Validform({
			tiptype : 3,
			tipSweep : true,
			showAllError : true	
			//postonce:true					
		});		
	});
</script>
</html>
