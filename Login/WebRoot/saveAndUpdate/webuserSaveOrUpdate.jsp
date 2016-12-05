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
	<form action="useradd" method="post" id="form">
		<table class="table table-condensed">
			<tr>
				<td class="td_show_title">帳號</td>
				<td class="td_input"><input type="text" name="webUsers.username" datatype="*1-50" id="username" onblur="check()" /><span id="error1"></span>
				</td>
				<td class="td_show_title">密碼</td>
				<td class="td_input"><input type="text" name="webUsers.pwd" value="" datatype="*3-20"></td>
			</tr>

			<tr>
				<td class="td_show_title">廠別</td>
				<s:if test="#session.factNo!='tw'">
					<td class="td_input"><input type="text" style="color:blue" name="webUsers.factno" value="${loginUser.factno}__${loginUser.erpfactno}" readonly id="dwr_factno" />
					</td>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<td class="td_input"><select style="color:blue" name="webUsers.factno" datatype="*" onchange="check(),checkEmail()" id="dwr_factno">
							
					</select>
					</td>
				</s:if>
				<td class="td_show_title">姓名</td>
				<td class="td_input"><input type="text" name="webUsers.name" datatype="*1-50" value=""></td>
			</tr>
			<tr>

				<td class="td_show_title">Email</td>
				<td class="td_input"><input type="text" name="webUsers.email" datatype="e" value="" id="email" onblur="checkEmail()" /><span id="error2"></span></td>
				<td class="td_show_title">工號</td>
				<td class="td_input"><input type="text" name="webUsers.workno" datatype="*1-20" />
				</td>
			</tr>
			<tr>

				<td class="td_show_title">IP</td>
				<td class="td_input"><input type="text" name="webUsers.ip" value=""></td>
				<td class="td_show_title">是否只讀</td>
				<td class="td_input"><select name="webUsers.userread">
						<option value="0">否</option>
						<option value="1">是</option>
				</select> <input type="hidden" name="webUsers.available" value="0" /></td>
			</tr>

		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; <input type="reset" id="reset" value="重置" class="btn btn-primary" />&nbsp;&nbsp;&nbsp; <input
				type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary" />

		</center>
	</form>

	<script type="text/javascript">
		jq(function() {
			var demo = jq("#form").Validform({
				btnSubmit : "#sub",
				tiptype : 4,
				tipSweep : true,
				showAllError : true,
				ajaxPost : true,
				callback : function(data) {
					if (data == "0") {
						layer.msg("提交成功!", 3, 1);
						//location.href="/Login/userfindPageBean";
						loadUrl("/Login/userfindPageBean");
					} else {
						alert(data.responseText);
					}
				}
			});

			jq
					.ajax({
						type : "get",
						dataType : "json",
						url : "webfact_findAllVwebfact",
						success : function(data) {
							var item = "";
							jq("#dwr_factno").empty();
							jq("#dwr_factno").append(
									"<option value=''>請選擇廠別</option>");
							jq
									.each(
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

		function back() {
			loadUrl("/Login/userfindPageBean");
		}
		function check() {
			var factno = document.getElementById("dwr_factno").value.split("__")[0];
			var username = document.getElementById("username").value;
			if (factno != "" && username != "") {
				userjs
						.findByIdDwr2(
								factno,
								username,
								function(x) {
									if (x != null) {
										alert("该厂已存在(" + username + ")");
										document.getElementById("sub").disabled = true;
										document.getElementById("sub").value = "已鎖定";
										document.getElementById("sub").style.color = "red";
										document.getElementById("error1").innerHTML = "<font color='color'>！</font>";
									} else {
										document.getElementById("sub").disabled = false;
										document.getElementById("sub").value = "確定";
										document.getElementById("sub").style.color = "white";
										document.getElementById("error1").innerHTML = "";
									}
								});
			}
		}
		function checkEmail() {
			var factno = document.getElementById("dwr_factno").value.split("__")[0];
			var email = document.getElementById("email").value;
			if (factno != "" && email != "") {
				userjs
						.findUserByFactNoAEmail(
								factno,
								email,
								function(x) {
									if (x != null) {
										alert("当前Email(" + email + ")已被使用");
										document.getElementById("sub").disabled = true;
										document.getElementById("sub").value = "已鎖定";
										document.getElementById("sub").style.color = "red";
										document.getElementById("error2").innerHTML = "<font color='color'>！</font>";
									} else {
										document.getElementById("sub").disabled = false;
										document.getElementById("sub").value = "確定";
										document.getElementById("sub").style.color = "white";
										document.getElementById("error2").innerHTML = "";
									}
								});
			}
		}
		jq(function() {
			goTrim();
		});
	</script>	
	<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
</body>
</html>
