<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>Insert title here</title>
<LINK href="css/list.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/form.css" />
<script type="text/javascript" src="jquery/jquery-1.7.2.js"></script>
<script type="text/javascript" src="jquery_alert_dialogs/jquery.js"></script>
<script type="text/javascript"
	src="jquery_alert_dialogs/jquery.alerts.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery_alert_dialogs/jquery.alerts.css" />
<script type="text/javascript"
	src="jquery_alert_dialogs/jquery.ui.draggable.js"></script>
<script type="text/javascript">
	function check() {
		var length = 0;
		var files = document.getElementsByName("files");
		for ( var j = 0; j < files.length; j++) {
			var val = files[j].value;
			var k = val.substr(val.indexOf("."));
			if (k == ".xls") {
				length++;
			}
		}
		if (length == files.length) {
			var biao = document.getElementById("biao").value;
			document.getElementById("uploadForm").setAttribute("action", biao);
			document.getElementById("uploadForm").submit();
		} else {
			if ($.browser.msie) {
				alert("你好,你選擇的文件當中有不是excel格式的!");
			} else {
				jAlert("你好,你選擇的文件當中有不是excel格式的!", '提示信息');
			}
		}
	}
	var i = 0;
	function addHTML() {
		i++;
		var d = document.getElementsByName("files");
		if (d.length < 11) {
			document.getElementById("more").innerHTML += "<table id='files"+i
			+"'><td class='td_show_title'><input type='file' name='files' /><td><td class='td_show_title'>"
					+ "<input value='刪除' type='button' onclick='deleteHtml(files"
					+ i + ")'></td></table>";
		} else {
			if ($.browser.msie) {
				alert("你好,只支持添加10個選擇框!");
			} else {
				jAlert("你好,只支持添加10個選擇框!", '提示信息');
			}
		}
	}
	function deleteHtml(id) {
		id.parentNode.removeChild(id);
	}
</script>
</head>
<body>
	<form id="uploadForm" enctype="multipart/form-data" method="post">
		<div id="more">
			<table>
				<tr>
					<td class="td_show_title"><input type="file" name="files" />
					</td>
					<td class="td_show_title"><input type="button" value="添加多個文件"
						onclick="addHTML();">
					</td>
					<td class="td_show_title">EXCEL數據對應的表名:</td>
					<td class="td_show_title"><select onchange="change()"
						name="dataName" id="biao">
							<option value="">請選擇</option>
							<option value="machine_excel">孔位數回轉數</option>
							<option value="count_excel">損益匯總</option>
							<option value="profit_excel">損益表</option>
							<option value="inv_excel">生產與請款</option>
							<option value="avprice_excel">平均粗胚單價</option>
							<option value="outback_excel">出貨與退貨</option>
							<option value="mastore_excel">全廠原料庫存</option>
							<option value="backcp_excel">回頭料</option>
							<option value="person_excel">人員效能</option>
							<option value="gw_excel">關務損失分析表</option>
							<option value="backmat_excel">回收粉使用統計</option>
							<option value="side_excel">邊料不良重量分析</option>
							<option value="cash_excel">水電油</option>
							<option value="mat_excel">色料藥品耗用</option>
							<option value="mat2_excel">離型劑耗用</option>
							<option value="store_excel">成品庫存</option>
					</select>
					</td>
					<td class="td_show_title"><input type="button" value="上传"
						onclick="check();">
					</td>
					<td><span style="color:red;">${message}</span>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<!-- <form action="filesUpload_goHome" method="post" enctype="multipart/form-data">
	  <input type="file" name="file"><input type="submit" value="上傳">
	   <a href="download_download">123.txt</a> 
	</form> -->
</body>
</html>