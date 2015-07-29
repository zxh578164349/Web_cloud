<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'StatisticalData.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="jquerys/DatePicker/WdatePicker.js"></script>
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript"
	src="jquery_alert_dialogs/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery_alert_dialogs/jquery.alerts.css" />
<script type="text/javascript"
	src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery/loding/ui.loading.css" />
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<script type="text/javascript">
	function check() {
		var startDate = $("#startDate").val();
		var endDate = $("#endDate").val();
		var d1, d2;
		d1 = new Date(startDate);
		d2 = new Date(endDate);
		var t = (d2.getYear() - d1.getYear()) * 12
				+ (d2.getMonth() - d1.getMonth());
		t = t + 1;
		var startNum = parseInt(startDate.replace(/-/g, ''), 10);
		var endNum = parseInt(endDate.replace(/-/g, ''), 10);
		var select = document.getElementById("select").value;
		var sel = document.getElementById("sel").value;
		if (startNum > endNum) {
			if ($.browser.msie) {
				alert("结束时间不能在开始时间之前！");
			} else {
				jAlert("结束时间不能在开始时间之前！", '提示信息');
			}
		} else if (t > 12) {
			if ($.browser.msie) {
				alert("不支持超出1年的數據統計！");
			} else {
				jAlert("不支持超出1年的數據統計！", '提示信息');
			}
		} else if (startDate == "" || startDate == null) {
			if ($.browser.msie) {
				alert("請選擇開始時間");
			} else {
				jAlert("請選擇開始時間", '提示信息');
			}
		} else if (endDate == "" || endDate == null) {
			if ($.browser.msie) {
				alert("請選擇結束時間");
			} else {
				jAlert("請選擇結束時間", '提示信息');
			}
		} else if (select == "0") {
			if ($.browser.msie) {
				alert("請選擇打印格式");
			} else {
				jAlert("請選擇打印格式", '提示信息');
			}
		} else if (sel == "") {
			if ($.browser.msie) {
				alert("請選擇廠別");
			} else {
				jAlert("請選擇廠別", '提示信息');
			}
		} else {
			document.getElementById("selectForm").submit();
			if ($.browser.msie) {
				alert("正在統計數據中,請等待！");
			} else {
				jAlert("正在統計數據中,請等待！", '提示信息');
			}
		}
	}
</script>
</head>
<body>
	<form id="selectForm" action="printData" method="post">
		<table>
			<tr>
				<td class="td_show_title">請點擊選擇統計的月份：</td>
				<td class="td_show_title"><input style="width:100px"
					id="startDate" type="text" name="yymm" onClick="WdatePicker()"
					readonly="readonly" />
				</td>
				<td>-</td>
				<td class="td_show_title"><input style="width:100px"
					id="endDate" type="text" name="yymms" onClick="WdatePicker()"
					readonly="readonly" />
				</td>
				<td class="td_show_title">請選擇打印的格式：</td>
				<td class="td_show_title"><select
					style="height:21px;margin-top:0px;" id="select" name="printType">
						<option value="0">請選擇</option>
						<option value="pdf">PDF</option>
						<option value="excel">EXCEL</option>
						<option value="word">WORD</option>
						<option value="html">HTML</option>
				</select>
				</td>
				<td>
				<td class="td_show_title">廠別 <s:if test="#attr.factNo=='tw'">
						<select id="sel" name="factNo">
							<option value="">請選擇</option>
							<s:iterator value="#attr.facts" id="temp">
								<option value="${temp[0]}">
									${temp[1]}&nbsp;(${temp[0]})</option>
							</s:iterator>
						</select>
					</s:if> <s:else>
						<select id="sel" name="factNo">
							<option value="">請選擇</option>
							<option value="<s:property value='#attr.factNo'/>">
								<s:property value="#attr.factName" />
							</option>
						</select>
					</s:else></td>
				<td><input value="打印" type="button" onclick="check()" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
