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
<head>
<base href="<%=basePath%>">

<title>My JSP 'addBackMat.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="../css/form.css" />
<link rel="stylesheet" type="text/css" href="../css/button_css.css" />
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
</head>

<body>
	<form action="" method="post">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">
			<tbody id="tb_list_info">
				<tr></tr>
			</tbody>
			<tbody id="tb_list_info2">
				<tr>
					<td class="td_show_title">用戶ID</td>
					<td class="td_input"><input type="text" value=""
						readonly="readonly">
					</td>
					<td class="td_show_title">登陸名</td>
					<td class="td_input"><input type="text"
						name="humanFile.humanName" value="" onblur="" id="humanName">
					</td>

				</tr>
				<tr>
					<td class="td_show_title">姓名</td>
					<td class="td_input"><input type="text"
						name="humanFile.humanBirthday" id="year" onClick="WdatePicker()"
						readonly />
					</td>
					<td class="td_show_title">密碼</td>
					<td class="td_input"><input type="text"
						name="humanFile.humanAddress" value="">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">登陸IP</td>
					<td class="td_input"><input type="text"
						name="humanFile.humanAddress" value="">
					</td>
					<td class="td_show_title">工號</td>
					<td class="td_input"><input type="text"
						name="humanFile.humanAddress" value="">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><input type="text"
						name="humanFile.humanAddress" value="">
					</td>
					<td class="td_show_title">狀態</td>
					<td class="td_input"><input type="text"
						name="humanFile.humanAddress" value="">
					</td>
				</tr>
			</tbody>
			<tbody>
			</tbody>
		</table>

		<center>
			<input type="submit" id="sub" name="submit" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/> <input
				type="reset" id="reset" name="submit" value="重置档案" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
		</center>
	</form>
</body>
</html>
