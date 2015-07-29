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
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>

<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 3,
			showAllError : true,
			datatype : {
				"*0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/
			}
		});

		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";

	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});
	}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>

<body>
	<form action="mat_addMats" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">
			<tbody id="tb_list_info">
				<tr></tr>
			</tbody>
			<tbody id="tb_list_info2">
				<s:if test="mats==null">
					<tr>
						<td class="td_show_title"><span id="sfactNo2"></span>廠別</td>
						<td class="td_input"><s:if test="#session.factNo!='tw'">
								<input type="text" style="color:blue" name="mats.id.factNo"
									value="${factNo}" readonly />
							</s:if> <s:if test="#session.factNo=='tw'">
								<select style="color:blue" name="mats.id.factNo"
									onchange="getFactArea(this.value)" datatype="*">
									<option value="">請選擇廠別</option>
									<s:iterator value="#session.facts" id="temp">
										<option value="${temp[0]}">
											${temp[1]}&nbsp;(${temp[0]})</option>
									</s:iterator>
								</select>
							</s:if> <span id="sfactNo"></span></td>
						<td class="td_show_title">單號</td>
						<td class="td_input"><input type="text" name="" disabled
							value="後台自動生成" id="billNo" onblur="clear1()"> <span
							id="sbillNo"></span></td>
					</tr>
					<tr>
						<td class="td_show_title"><span id="syymm2"></span>年月</td>
						<td class="td_input">年 <span><select name="year"
								class="td_input" id="nyear" datatype="*">
									<option value="">請選擇年</option>
									<option value="2008">2008年</option>
									<option value="2009">2009年</option>
									<option value="2010">2010年</option>
									<option value="2011">2011年</option>
									<option value="2012">2012年</option>
									<option value="2013">2013年</option>
									<option value="2014">2014年</option>
									<option value="2015">2015年</option>
									<option value="2016">2016年</option>
									<option value="2017">2017年</option>
									<option value="2018">2018年</option>
									<option value="2019">2019年</option>
									<option value="2020">2020年</option>
							</select> </span> 月 <span><select name="month" class="td_input"
								id="nmonth" datatype="*">
									<option value="">請選擇月份</option>
									<option value="01">1月</option>
									<option value="02">2月</option>
									<option value="03">3月</option>
									<option value="04">4月</option>
									<option value="05">5月</option>
									<option value="06">6月</option>
									<option value="07">7月</option>
									<option value="08">8月</option>
									<option value="09">9月</option>
									<option value="10">10月</option>
									<option value="11">11月</option>
									<option value="12">12月</option>
							</select> </span> <!-- <input type="text" name="mats.id.yymm"   onClick="WdatePicker()" id="yymm"  onblur="clear1()" value="系統當前日期" disabled/> -->
							<span id="syymm"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><s:if test="#session.factNo=='tw'">
								<select name="mats.factCode" id="dwrFactArea" datatype="*">
									<option value="">請選擇廠別狀態</option>
								</select>
							</s:if> <s:else>
								<select name="mats.factCode" datatype="*">
									<option value="">請選擇廠別狀態</option>
									<s:iterator value="#session.factAreas_login" id="temp">
										<option value="${temp}">${temp}</option>
									</s:iterator>
								</select>
							</s:else></td>
					</tr>
				</s:if>
				<s:if test="mats!=null">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='mats.id.factNo'/>"
								name="mats.id.factNo" readonly /> </font>
						</td>
						<td class="td_show_title">單號</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="billNo" style="color:blue"
								value="<s:property value='mats.id.billNo'/>"
								name="mats.id.billNo" readonly /> </font>
						</td>
					</tr>
					<tr>
						<td class="td_show_title">年月</td>
						<td class="td_input"><input type="text" style="color:blue"
							id="yymm" value="<s:date name='mats.id.yymm' format='yyyyMM'/>"
							name="yymm" readonly /></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="mats.factCode"
							datatype="*">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_other" id="temp">
									<s:if test="#attr.temp==mats.factCode">
										<option value="${temp}" selected>${temp}</option>
									</s:if>
									<s:else>
										<option value="${temp}">${temp}</option>
									</s:else>
								</s:iterator>
						</select></td>
					</tr>
				</s:if>
				<tr>
					<td class="td_show_title">色料</td>
					<td class="td_input"><input type="text" name="mats.matC"
						value="<s:property value='mats.matC'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">藥品</td>
					<td class="td_input"><input type="text" name="mats.matS"
						value="<s:property value='mats.matS'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">數量單耗</td>
					<td class="td_input"><input type="text"
						name="mats.countDanhao"
						value="<s:property value='mats.countDanhao'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">金額單耗</td>
					<td class="td_input"><input type="text"
						name="mats.moneyDanhao"
						value="<s:property value='mats.moneyDanhao'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">日期起</td>
					<td class="td_input"><input type="text" name="mats.dateB"
						value="<s:property value='mats.dateB'/>" onClick="WdatePicker()"
						readonly class="Wdate"></td>

					<td class="td_show_title">日期止</td>
					<td class="td_input"><input type="text" name="mats.dateE"
						value="<s:property value='mats.dateE'/>" onClick="WdatePicker()"
						readonly class="Wdate" /></td>

				</tr>
				<tr>
					<td class="td_show_title">鎖定</td>
					<td class="td_input"><input type="text" name="mats.lockMk"
						value="<s:property value='mats.lockMk'/>">
					</td>
					<td class="td_show_title">輸入者工號</td>
					<td class="td_input"><input type="text" name="mats.userNo"
						id="year" value="${loginUser.workno}" style="color:blue" readonly />
					</td>
				</tr>
				<tr>
					<td class="td_show_title">輸入時間</td>
					<td class="td_input"><input type="text" name="mats.dateTime"
						onClick="WdatePicker()" value="系統當前時間" disabled>
					</td>
				</tr>
			</tbody>
			<tbody>
			</tbody>
		</table>
		<s:if test="mats==null">
			<center>
				<input type="button" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</center>
		</s:if>
		<s:if test="mats!=null">
			<center>
				<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</center>
		</s:if>
	</form>
</body>
</html>
