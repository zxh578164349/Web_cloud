<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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

	<ul id="myTab" class="nav nav-tabs">
		<li class="active">
			<a href="#tab_user" data-toggle="tab" id="a_user">使用者類型</a>
		</li>
		<li>
			<a href="#tab_guest" data-toggle="tab" id="a_guest">訪客類型</a>
		</li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div class="tab-pane fade in active" id="tab_user">
			<div>
				<jsp:include page="webuserSaveOrUpdate_user.jsp" />
			</div>
		</div>
		<div class="tab-pane fade" id="tab_guest">
			<div>
				<jsp:include page="../guest/saveAndUpdate/webuserSaveOrUpdate_guest.jsp" />
			</div>
		</div>
	</div>
	<script type="text/javascript">
		jq(function() {
			goTrim();

		});
		function back() {
			loadUrl("/Login/userfindPageBean");
		}

		function check(factno, username,sub,error) {
			var factno = document.getElementById(factno).value.split("__")[0];
			var username = document.getElementById(username).value;
			if (factno != "" && username != "") {
				userjs
						.findByIdDwr2(
								factno,
								username,
								function(x) {
									if (x != null) {
										alert("當前用戶(" + username + ")已存在");
										document.getElementById(sub).disabled = true;
										document.getElementById(sub).value = "已鎖定";
										document.getElementById(sub).style.color = "red";
										document.getElementById(error).innerHTML = "<font color='color'>！</font>";
									} else {
										document.getElementById(sub).disabled = false;
										document.getElementById(sub).value = "確定";
										document.getElementById(sub).style.color = "white";
										document.getElementById(error).innerHTML = "";
									}
								});
			}
		}
		function checkEmail(factno, email,sub,error) {
			var factno = document.getElementById(factno).value.split("__")[0];
			var email = document.getElementById(email).value;
			if (factno != "" && email != "") {
				userjs
						.findUserByFactNoAEmail(
								factno,
								email,
								function(x) {
									if (x != null) {
										alert("当前Email(" + email + ")已被使用");
										document.getElementById(sub).disabled = true;
										document.getElementById(sub).value = "已鎖定";
										document.getElementById(sub).style.color = "red";
										document.getElementById(error).innerHTML = "<font color='color'>！</font>";
									} else {
										document.getElementById(sub).disabled = false;
										document.getElementById(sub).value = "確定";
										document.getElementById(sub).style.color = "white";
										document.getElementById(error).innerHTML = "";
									}
								});
			}
		}
	</script>
	<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
</body>
</html>
