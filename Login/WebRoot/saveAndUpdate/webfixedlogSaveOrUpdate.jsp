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

<title>My JSP 'fixedSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		j("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			callback : function(form) {
				document.getElementById("mydiv").style.display = "block";
				form[0].submit();
			}
		});

	});

	function back() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			document.getElementById("mydiv").style.display = "block";
			window.location.href = "../fix_findPageBean";
		} else {
			document.getElementById("mydiv").style.display = "block";
			window.location.href = "fix_findPageBean";
		}
	}

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webmajorjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/websubjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webfixjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>
<body onload="init()">
	<form action="fix_addlog" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">

			<tr>
				<td class="td_show_title_color" width="10%">廠別(From)</td>
				<td><input type="text" value="<s:property value='fix.factNo'/>"
					name="log.factnoFrom" readonly style="color:blue" />
				</td>
				<td class="td_show_title_color">廠別狀態(From)</td>
				<td><input type="text"
					value="<s:property value='fix.factcode'/>" name="log.factcodeFrom"
					readonly style="color:blue" />
				</td>
			</tr>


			<s:if test="#session.factNo!='tw'">
				<tr>
					<td class="td_show_title_color" width="10%">廠別(To)</td>
					<td class="td_input"><input type="text" style="color:blue"
						name="log.factnoTo" value="${factNo}" readonly id="dwrFactNo" />
					</td>
					<td class="td_show_title_color">廠別狀態(To)</td>
					<td class="td_input"><select name="log.factcodeTo"
						datatype="*" id="factarea">
							<option value="">請選擇廠別狀態</option>
							<s:iterator value="#session.factAreas_login" id="temp">
								<option value="${temp}">${temp}</option>
							</s:iterator>
					</select></td>
				</tr>
			</s:if>

			<s:if test="#session.factNo=='tw'">
				<tr>
					<td class="td_show_title_color">廠別(To)</td>
					<td class="td_input"><select name="log.factnoTo" datatype="*"
						id="dwrFactNo"
						onchange="getFactArea(this.value),makeSsetscoding()">
							<option value="">請選擇廠別</option>
							<s:iterator value="#session.facts" id="temp">
								<option value="${temp[0]}">${temp[1]}&nbsp;(${temp[0]})</option>
							</s:iterator>
					</select></td>
					<td class="td_show_title_color">廠別狀態(To)</td>
					<td class="td_input"><select name="log.factcodeTo"
						datatype="*" id="dwrFactArea">
							<option value="">請選擇廠別狀態</option>
					</select></td>
				</tr>
			</s:if>



			<tr>
				<td class="td_show_title_color">調撥時間</td>
				<td class="td_input"><input type="text" value=""
					name="log.changedate" onClick="WdatePicker()" class="Wdate"
					datatype="*" /></td>


				<td class="td_show_title_color">財產編號</td>
				<td class="td_input"><input type="text"
					value="<s:property value='fix.assetscoding'/>" readonly
					name="log.assetscoding" style="color:blue" /></td>

			</tr>

			<tr>

				<td class="td_show_title_color">廠內財編</td>
				<td class="td_input"><input type="text" value=""
					name="log.fixedId" datatype="*0-30" /></td>

				<td class="td_show_title_color">名稱</td>
				<td class="td_input"><input type="text"
					value="<s:property value='fix.assetname'/>" name="log.assetname"
					datatype="*0-100" /> <input type="hidden"
					value="<s:property value='fix.fixedassetsId'/>"
					name="fixedassetsId" /></td>

			</tr>


			<tr>
				<td colspan="4"><center>
						<input type="button" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;

						<s:if test="fix!=null">
							<input type="button" value="返回"
								onclick="javascript:document.getElementById('mydiv').style.display='block',location.href='fix_findPageBean'" id="btn_back"
								 onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
						</s:if>
						<s:else>
							<input type="button" value="返回" onclick="back()" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
						</s:else>

					</center>
				</td>
			</tr>


		</table>
	</form>
	<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>
</body>
</html>
