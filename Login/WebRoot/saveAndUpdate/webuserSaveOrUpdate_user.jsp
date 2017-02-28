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
	<form action="useradd" method="post" id="form_user">
		<table class="table table-condensed">		   
			<tr>
				<td >帳號</td>
				<td ><input type="text" name="webUsers.username" datatype="*1-50" id="username" onblur="check('dwr_factno','username','sub_user','error1')" /><span id="error1"></span>
				</td>
				<td >密碼</td>
				<td ><input type="text" name="webUsers.pwd" value="" datatype="*3-20"></td>
			</tr>

			<tr>
				<td >廠別</td>
				<s:if test="#session.factNo!='tw'">
					<td >
					<input type="text" style="color:blue" name="webUsers.factno" value="${loginUser.factno}__${loginUser.erpfactno}" readonly id="dwr_factno" />
					</td>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<td ><select style="color:blue" name="webUsers.factno" datatype="*" onchange="check('dwr_factno','username','sub_user','error1'),checkEmail('dwr_factno','email','sub_user','error2')" id="dwr_factno">							
					</select>
					</td>
				</s:if>
				<td >姓名</td>
				<td ><input type="text" name="webUsers.name" datatype="*1-50" value=""></td>
			</tr>
			<tr>

				<td >Email</td>
				<td ><input type="text" name="webUsers.email" datatype="e" value="" id="email" onblur="checkEmail('dwr_factno','email','sub_user','error2')" /><span id="error2"></span></td>
				<td >工號</td>
				<td ><input type="text" name="webUsers.workno" datatype="*0-20" />
				</td>
			</tr>
			<tr>

				<td >IP</td>
				<td ><input type="text" name="webUsers.ip" value=""></td>
				<td >是否只讀</td>
				<td ><select name="webUsers.userread" id="userread">
						<option value="0">否</option>
						<option value="1">是</option>
				</select>
				 <input type="hidden" name="webUsers.available" value="0" />
				 <input type="hidden" name="webUsers.userType" value="0"/><%--使用者類型--%>
				 </td>
			</tr>

		</table>
		<center>
			<input type="button" id="sub_user" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp;
			 <input type="reset"  value="重置" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; 
			 <input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary" />				
		</center>
	</form>

	<script type="text/javascript">
		jq(function() {
			var demo = jq("#form_user").Validform({
				btnSubmit : "#sub_user",
				tiptype : 4,
				tipSweep : true,
				showAllError : true,
				ajaxPost : true,
				callback : function(data) {
					if (data == "0") {
						layer.msg("提交成功!", 3, 1);
						loadUrl("userfindPageBean");
					} else {
						layer.msg("提交失敗!", 3, 3);
					}
				}
			});			
		 jq.ajax({
						type : "get",
						dataType : "json",
						url : "webfact_findAllVwebfact",
						success : function(data) {
							var item = "";
							jq("#dwr_factno").empty();
							jq("#dwr_factno").append("<option value=''>請選擇廠別</option><option value='tw__tw'>TW</option>");									
							jq.each(
											data,
											function(i, obj) {
												item += "<option value='"+obj[0]+"__"+obj[3]+"'>"
														+ obj[1]
														+ "("
														+ obj[3]
														+ ")</option>";
											});
							jq("#dwr_factno").append(item);
						}
					});		 
		});
		
			
	</script>	
</body>
</html>
