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
	<form action="person_addPerson" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">
			<tbody id="tb_list_info">
				<tr></tr>
			</tbody>
			<tbody id="tb_list_info2">
				<s:if test="person==null">
					<tr>
						<td class="td_show_title"><span id="sfactNo2"></span>廠別</td>
						<td class="td_input"><s:if test="#session.factNo!='tw'">
								<input type="text" style="color:blue" name="person.id.factNo"
									value="${factNo}" readonly />
							</s:if> <s:if test="#session.factNo=='tw'">
								<select style="color:blue" name="person.id.factNo" datatype="*"
									onchange="getFactArea(this.value)">
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
							</select> </span> <!-- <input type="text" name="person.id.yymm"   onClick="WdatePicker()" id="yymm"  onblur="clear1()" value="系統當前日期" disabled/> -->
							<span id="syymm"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><s:if test="#session.factNo=='tw'">
								<select name="person.factCode" id="dwrFactArea" datatype="*">
									<option value="">請選擇廠別狀態</option>
								</select>
							</s:if> <s:else>
								<select name="person.factCode" datatype="*">
									<option value="">請選擇廠別狀態</option>
									<s:iterator value="#session.factAreas_login" id="temp">
										<option value="${temp}">${temp}</option>
									</s:iterator>
								</select>
							</s:else></td>
					</tr>
				</s:if>
				<s:if test="person!=null">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='person.id.factNo'/>"
								name="person.id.factNo" readonly /> </font>
						</td>
						<td class="td_show_title">單號</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="billNo" style="color:blue"
								value="<s:property value='person.id.billNo'/>"
								name="person.id.billNo" readonly /> </font>
						</td>
					</tr>
					<tr>
						<td class="td_show_title">年月</td>
						<td class="td_input"><input type="text" style="color:blue"
							id="yymm" value="<s:date name='person.id.yymm' format='yyyyMM'/>"
							name="yymm" readonly /></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="person.factCode"
							datatype="*">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_other" id="temp">
									<s:if test="#attr.temp==person.factCode">
										<option value="${temp}" selected>${temp}</option>
									</s:if>
									<s:else>
										<option value="${temp}">${temp}</option>
									</s:else>
								</s:iterator>
						</select>
						</td>
					</tr>
				</s:if>
				<tr>
					<td class="td_show_title">標準產能</td>
					<td class="td_input"><input type="text"
						name="person.demoStand"
						value="<s:property value='person.demoStand'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">實際產能</td>
					<td class="td_input"><input type="text" name="person.demoAct"
						value="<s:property value='person.demoAct'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">直工人數</td>
					<td class="td_input"><input type="text" name="person.personZg"
						value="<s:property value='person.personZg'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">間工人數</td>
					<td class="td_input"><input type="text" name="person.personJg"
						value="<s:property value='person.personJg'/>" datatype="*0-6" />
					</td>
				</tr>
				<tr>
					<td class="td_show_title">全廠總人數</td>
					<td class="td_input"><input type="text"
						name="person.personCount"
						value="<s:property value='person.personCount' />" datatype="*0-6">
					</td>
					<td class="td_show_title">直間比</td>
					<td class="td_input"><input type="text"
						name="person.personZjg"
						value="<s:property value='person.personZjg'/>" datatype="*0-6" />
					</td>
				</tr>
				<tr>
					<td class="td_show_title">直工人均產能(模人)</td>
					<td class="td_input"><input type="text"
						name="person.personZgavg"
						value="<s:property value='person.personZgavg' />" datatype="*0-6">
					</td>
					<td class="td_show_title">全廠人均產能(模人)</td>
					<td class="td_input"><input type="text"
						name="person.personAvg"
						value="<s:property value='person.personAvg'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">全廠人均產能(模h)</td>
					<td class="td_input"><input type="text"
						name="person.personAvgh"
						value="<s:property value='person.personAvgh'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">直工離職率(%)</td>
					<td class="td_input"><input type="text" name="person.leaveZg"
						value="<s:property value='person.leaveZg'/>" datatype="*0-6" />
					</td>
				</tr>
				<tr>
					<td class="td_show_title">全廠離職率(%)</td>
					<td class="td_input"><input type="text"
						name="person.leaveCount"
						value="<s:property value='person.leaveCount'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">直工薪資</td>
					<td class="td_input"><input type="text" name="person.wageZg"
						value="<s:property value='person.wageZg'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">間工薪資</td>
					<td class="td_input"><input type="text" name="person.wageJg"
						value="<s:property value='person.wageJg'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">總薪資</td>
					<td class="td_input"><input type="text"
						name="person.wageCount"
						value="<s:property value='person.wageCount'/>" datatype="*0-6" />
					</td>
				</tr>
				<tr>
					<td class="td_show_title">總工時</td>
					<td class="td_input"><input type="text"
						name="person.timeCount"
						value="<s:property value='person.timeCount'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">時產能</td>
					<td class="td_input"><input type="text" name="person.outputHr"
						value="<s:property value='person.outputHr'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">每雙單耗</td>
					<td class="td_input"><input type="text"
						name="person.profitPair"
						value="<s:property value='person.profitPair'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">直工加班費</td>
					<td class="td_input"><input type="text" name="person.addZg"
						value="<s:property value='person.addZg'/>" datatype="*0-6" />
					</td>
				</tr>
				<tr>
					<td class="td_show_title">間工加班費</td>
					<td class="td_input"><input type="text" name="person.addJg"
						value="<s:property value='person.addJg'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">總加班費</td>
					<td class="td_input"><input type="text" name="person.addCount"
						value="<s:property value='person.addCount'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">直工人均薪資</td>
					<td class="td_input"><input type="text"
						name="person.wageZgavg"
						value="<s:property value='person.wageZgavg'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">間工人均薪資</td>
					<td class="td_input"><input type="text"
						name="person.wageJgavg"
						value="<s:property value='person.wageJgavg'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">直工占薪資比例</td>
					<td class="td_input"><input type="text"
						name="person.wageZgexp"
						value="<s:property value='person.wageZgexp'/>" datatype="*0-6" />
					</td>
					<td class="td_show_title">間工占薪資比例</td>
					<td class="td_input"><input type="text"
						name="person.wageJgexp"
						value="<s:property value='person.wageJgexp'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">全廠人均薪資</td>
					<td class="td_input"><input type="text" name="person.wageAvg"
						value="<s:property value='person.wageAvg'/>" datatype="*0-6">
					</td>
					<td class="td_show_title">全廠人均產能</td>
					<td class="td_input"><input type="text"
						name="person.personAvghz"
						value="<s:property value='person.personAvghz'/>" datatype="*0-6">
					</td>
				</tr>
				<tr>
					<td class="td_show_title">日期起</td>
					<td class="td_input"><input type="text" name="person.dateB"
						value="<s:property value='person.dateB'/>" id="year"
						onClick="WdatePicker()" readonly class="Wdate" /></td>
					<td class="td_show_title">日期止</td>
					<td class="td_input"><input type="text" name="person.dateE"
						value="<s:property value='person.dateE'/>" onClick="WdatePicker()"
						readonly class="Wdate"></td>
				</tr>
				<tr>
					<td class="td_show_title">鎖定</td>
					<td class="td_input"><input type="text" name="person.lockMk"
						value="<s:property value='person.lockMk'/>">
					</td>
					<td class="td_show_title">輸入者工號</td>
					<td class="td_input"><input type="text" name="person.userNo"
						value="${loginUser.workno}" style="color:blue" readonly>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">輸入時間</td>
					<td class="td_input"><input type="text" name="person.dateTime"
						onClick="WdatePicker()" value="系統當前時間" disabled>
					</td>
				</tr>
			</tbody>
			<tbody>
			</tbody>
		</table>
		<s:if test="person==null">
			<center>
				<input type="button" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</center>
		</s:if>
		<s:if test="person!=null">
			<center>
				<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
					type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</center>
		</s:if>
	</form>
</body>
</html>
