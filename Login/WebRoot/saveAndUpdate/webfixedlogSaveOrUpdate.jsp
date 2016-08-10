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

</head>
<body >
	<form action="fix_addlog" method="post" id="form">
		<table class="table table-condensed"
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
						<input type="button" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;						
							<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>																												
					</center>
				</td>
			</tr>


		</table>
	</form>


<script type="text/javascript">
	jq(function() {
		jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			ajaxPost:true,
			callback : function(data) {
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="/Login/fix_findPageBean";
					loadUrl("/Login/fix_findPageBean");
				}
				if(data=="1"){
					//alert(data.responseText);
					layer.msg("提交失敗",3,3);
				}
				if(data=="2"){
					layer.msg("數據已存在,請重新調撥",3,3);
				}
			}
		});

	});

	function back() {
		loadUrl("/Login/fix_findPageBean3?backIndex=1");
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
</body>
</html>
