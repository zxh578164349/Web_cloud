<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<title>添加新用户</title>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>



<body>
	<form action="useradd" method="post" id="form_guest">
		<table class="table table-condensed">		    
			<tr>
				<td >帳號</td>
				<td ><input type="text" name="webUsers.username" datatype="*1-50" id="username2" onblur="check('dwr_factno2','username2','sub_guest','error3')" />
				<span id="error3"></span>
				</td>
				<td >密碼</td>
				<td ><input type="text" name="webUsers.pwd" value="" datatype="*3-20"></td>
			</tr>

			<tr>				
				<td >姓名</td>
				<td ><input type="text" name="webUsers.name" datatype="*1-50" value=""></td>
				<td >Email</td>
				<td ><input type="text" name="webUsers.email" datatype="e" value="" id="email2" onblur="checkEmail('dwr_factno2','email2','sub_guest','error4')" />
				<span id="error4"></span>
				<input type="hidden" name="webUsers.available" value="0" />
				<input type="hidden" name="webUsers.factno" value="1__1" id="dwr_factno2"/>
				<input type="hidden" name="webUsers.userread" value="1"/><%--只讀--%>
				<input type="hidden" name="webUsers.userType" value="1"/><%--訪客類型--%>				
				</td>					
			</tr>						
		</table>
		<center>
			<input type="button" id="sub_guest" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; 
			<input type="reset"  value="重置" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary" />
				

		</center>
	</form>

	<script type="text/javascript">
		jq(function() {
			var demo = jq("#form_guest").Validform({
				btnSubmit : "#sub_guest",
				tiptype : 4,
				tipSweep : true,
				showAllError : true,
				ajaxPost : true,
				callback : function(data) {
					if (data == "0") {
						layer.msg("提交成功!", 3, 1);					
						loadUrl("/Login/userfindPageBean");
					} else {
						layer.msg("提交失敗",3,3);
					}
				}
			});
		 
		});									
	</script>		
</body>
</html>
