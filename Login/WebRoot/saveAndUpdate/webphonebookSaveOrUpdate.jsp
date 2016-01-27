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

<!DOCTYPE HTML>
<html>
<title>新添聯系資料</title>
<head>
<base href="<%=basePath%>">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
</head>
<script type="text/javascript">
	var jq = jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function() {
		loadi = layer.load("正在提交,請稍等...");
	});
	jq(document).ajaxStop(function() {
		layer.close(loadi);
	});
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
				} else {
					alert(data.responseText);
				}
			}
		});
	});

	function back() {
		layer.load("正在返回,請稍等...");
		location.href = "/Login/webphonebook_findPageBean3?backIndex=1";
	}
	function check() {
		var factno = document.getElementById("dwr_factno").value;
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
		var factno = document.getElementById("dwr_factno").value;
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

	/*禁止空格輸入*/
	window.onload = function() {
		var inputs = document.getElementsByTagName("input");
		for ( var i = 0; i < inputs.length; i++) {
			if (inputs[i].getAttribute("type") == "text")
				inputs[i].onkeyup = function() {
					this.value = this.value.replace(/(^\s+)|\s+$/g, "");
				};
		}
	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript'
	src='/Login/dwr/interface/webbackfeetjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/userjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="webphonebook_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
			<caption>新添聯系資料</caption>
			<tr>
				<td class="td_show_title">廠別</td>
				<td class="td_input"><s:if test="webphone==null">
						<s:if test="#session.factNo!='tw'">
							<input type="text" style="color:blue" name="webphone.fact.factNo"
								value="${factNo}" readonly id="dwr_factno" />
						</s:if>
						<s:else>
							<select style="color:blue" name="webphone.fact.factNo"
								datatype="*"  id="dwr_factno">
								<option value="">請選擇廠別</option>
								<option value="tw">TW</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
							</select>
						</s:else>
					</s:if> 
					<s:else>
						<input type="text" name="webphone.fact.factNo"
							value="<s:property value='webphone.fact.factNo'/>" readonly
							style="color:blue" />
						<input type="hidden" name="pbId" value="<s:property value='webphone.pbId'/>"/>
					</s:else>
				</td>
				<td class="td_show_title">姓名</td>
				<td class="td_input"><input type="text"
					name="webphone.username" datatype="*1-50"
					value="<s:property value='webphone.username'/>"></td>
			</tr>
			<tr>
				<td class="td_show_title">部門</td>
				<td class="td_input"><input type="text"
					name="webphone.department" datatype="*1-50"
					value="<s:property value='webphone.department'/>" /></td>
				<td class="td_show_title">職務</td>
				<td class="td_input"><input type="text" name="webphone.post"
					value="<s:property value='webphone.post'/>" datatype="*1-30">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">內線</td>
				<td class="td_input"><input type="text" name="webphone.phoneA"
					value="<s:property value='webphone.phoneA'/>" 
					onblur="checkEmail()" /></td>
				<td class="td_show_title">手機</td>
				<td class="td_input"><input type="text" name="webphone.phoneB"
					datatype="*1-20" value="<s:property value='webphone.phoneB'/>" />
				</td>
			</tr>
			<tr>
				<td class="td_show_title">短號</td>
				<td class="td_input"><input type="text" name="webphone.phoneC"
					value="<s:property value='webphone.phoneC'/>" /></td>
				<td class="td_show_title">Email</td>
				<td class="td_input"><input type="text" name="webphone.email"
					value="<s:property value='webphone.email'/>" datatype="e"></td>
			</tr>
			<tr>
				<td class="td_show_title">QQ</td>
				<td class="td_input"><input type="text" name="webphone.qq"
					value="<s:property value='webphone.qq'/>" /></td>
				<td class="td_show_title">微信</td>
				<td class="td_input"><input type="text" name="webphone.weixin"
					value="<s:property value='webphone.weixin'/>" ></td>
			</tr>
			<tr>
				<td class="td_show_title">LINK</td>
				<td class="td_input"><input type="text" name="webphone.link"
					value="<s:property value='webphone.link'/>" />
					<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="webphone.creater"/>
					</td>
				
			</tr>

		</table>
		<center>
			<input type="submit" id="sub" value="確定"
				onmouseover="this.style.backgroundPosition='left -40px'"
				onmouseout="this.style.backgroundPosition='left top'" />&nbsp;&nbsp;&nbsp;
			<input type="reset" id="reset" value="重置"
				onmouseover="this.style.backgroundPosition='left -40px'"
				onmouseout="this.style.backgroundPosition='left top'" />&nbsp;&nbsp;&nbsp;
			<input type="button" value="返回" onclick="back()" id="btn_back"
				onmouseover="this.style.backgroundPosition='left -40px'"
				onmouseout="this.style.backgroundPosition='left top'" />

		</center>
	</form>

</body>
</html>
