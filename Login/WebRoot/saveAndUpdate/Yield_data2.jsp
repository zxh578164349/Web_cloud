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

<title>My JSP 'Yield_data.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>
<script type="text/javascript">
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			ignoreHidden : true,			
			datatype : {
				"*0-6" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
				"*1-6" : /^-?[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
				"*0-7" : /^-?\d{1,7}(\.[0-9]{1})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
				}else{
					alert(data.responseText);
				}				
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位以內小數";

	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});
	}

/* 	function getFactArea2(mid) {
		document.getElementById("dwrFactArea2").length = 1;
		webfactjs.findFactCodeByFactNo_show(mid, function(x) {
			dwr.util.addOptions("dwrFactArea2", x);
		});
	} */

	function back() {		
		loadUrl("ydata_findPageBean3?backIndex=1");		
	}

	function holiday() {
		var workholiday = document.getElementById("workholiday");
		if (workholiday.value == 1) {
			//alert("dfdf");
			document.getElementById("div1").style.display = "none";
			document.getElementById("div2").style.display = "block";
		}
		if (workholiday.value == 0) {
			//alert("dfdf");
			document.getElementById("div1").style.display = "block";
			document.getElementById("div2").style.display = "none";
		}
	}

	function showRow() {
		var renshu = document.getElementById("renshu");
		var shangmoshu = document.getElementById("shangmoshu");
		var bzcl = document.getElementById("bzcl");
		var sjcl = document.getElementById("sjcl");
		var tianshu = document.getElementById("tianshu");			
		var workholiday = document.getElementById("workholiday");
		
		var in_actualpairs=document.getElementById("in_actualpairs");
		var in_hostpairs=document.getElementById("in_hostpairs");
		var in_factpairs=document.getElementById("in_factpairs");
		var in_samplepairs=document.getElementById("in_samplepairs");
		var in_outnum=document.getElementById("in_outnum");
		var in_backnum=document.getElementById("in_backnum");
		var in_workhours=document.getElementById("in_workhours");
		if (workholiday.value == 1 || workholiday.value == 2) {
			renshu.style.display = "none";
			shangmoshu.style.display = "none";
			bzcl.style.display = "none";
			sjcl.style.display = "none";
			tianshu.style.display = "none";
			
			in_actualpairs.style.display="none";
			in_hostpairs.style.display="none";
			in_factpairs.style.display="none";
			in_samplepairs.style.display="none";
			in_outnum.style.display="none";
			in_backnum.style.display="none";
			in_workhours.style.display="none";

			document.getElementById("personnum").style.display = "none";
			document.getElementById("s_onModulus").style.display = "none";
			document.getElementById("standardOutput").style.display = "none";
			document.getElementById("actualYield").style.display = "none";
			document.getElementById("daycount").style.display = "none";
			
			document.getElementById("actualpairs").style.display = "none";
			document.getElementById("hostpairs").style.display = "none";
			document.getElementById("factpairs").style.display = "none";
			document.getElementById("samplepairs").style.display = "none";
			document.getElementById("outnum").style.display = "none";
			document.getElementById("backnum").style.display = "none";
			document.getElementById("workhours").style.display="none";
			renshu.value = "";
			shangmoshu.value = "";
			bzcl.value = "";
			sjcl.value = "";
			tianshu.value = "";
			
			in_actualpairs.value="";
			in_hostpairs.value="";
			in_factpairs.value="";
			in_samplepairs.value="";
			in_outnum.value="";
			in_backnum.value="";
			in_workhours.value="";

		}

		if (workholiday.value == 0) {
			renshu.style.display = "block";
			shangmoshu.style.display = "block";
			bzcl.style.display = "block";
			sjcl.style.display = "block";
			tianshu.style.display = "block";
			
			in_actualpairs.style.display="block";
			in_hostpairs.style.display="block";
			in_factpairs.style.display="block";
			in_samplepairs.style.display="block";
			in_outnum.style.display="block";
			in_backnum.style.display="block";
			in_workhours.style.display="block";

			document.getElementById("personnum").style.display = "block";
			document.getElementById("s_onModulus").style.display = "block";
			document.getElementById("standardOutput").style.display = "block";
			document.getElementById("actualYield").style.display = "block";
			document.getElementById("daycount").style.display = "block";
			
			document.getElementById("actualpairs").style.display = "block";
			document.getElementById("hostpairs").style.display = "block";
			document.getElementById("factpairs").style.display = "block";
			document.getElementById("samplepairs").style.display = "block";
			document.getElementById("outnum").style.display = "block";
			document.getElementById("backnum").style.display = "block";
			document.getElementById("workhours").style.display="block";

		}

	}

	function init() {
		var renshu = document.getElementById("renshu");
		var shangmoshu = document.getElementById("shangmoshu");
		var bzcl = document.getElementById("bzcl");
		var sjcl = document.getElementById("sjcl");
		var tianshu = document.getElementById("tianshu");
		
		var in_actualpairs=document.getElementById("in_actualpairs");
		var in_hostpairs=document.getElementById("in_hostpairs");
		var in_factpairs=document.getElementById("in_factpairs");
		var in_samplepairs=document.getElementById("in_samplepairs");
		var in_outnum=document.getElementById("in_outnum");
		var in_backnum=document.getElementById("in_backnum");
		var in_workhours=document.getElementById("in_workhours");

		renshu.style.display = "none";
		shangmoshu.style.display = "none";
		bzcl.style.display = "none";
		sjcl.style.display = "none";
		tianshu.style.display = "none";
		
		in_actualpairs.style.display="none";
		in_hostpairs.style.display="none";
		in_factpairs.style.display="none";
		in_samplepairs.style.display="none";
		in_outnum.style.display="none";
		in_backnum.style.display="none";
		in_workhours.style.display="none";

		document.getElementById("personnum").style.display = "none";
		document.getElementById("s_onModulus").style.display = "none";
		document.getElementById("standardOutput").style.display = "none";
		document.getElementById("actualYield").style.display = "none";
		document.getElementById("daycount").style.display = "none";
		
		document.getElementById("actualpairs").style.display = "none";
		document.getElementById("hostpairs").style.display = "none";
		document.getElementById("factpairs").style.display = "none";
		document.getElementById("samplepairs").style.display = "none";
		document.getElementById("outnum").style.display = "none";
		document.getElementById("backnum").style.display = "none";
		document.getElementById("workhours").style.display="none";						
	}
	

</script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>

<body onload="init()">

	<form action="ydata_addData" method="post" id="form">
		<table class="table table-condensed" id="table1">
			<s:if test="ydata==null">

				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title" width="10%">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="ydata.id.factNo" value="${factNo}" readonly id="factno" />
						</td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode"
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
						<td class="td_show_title">廠別</td>
						<td class="td_input"><select style="color:blue"
							name="ydata.id.factNo" datatype="*" id="dwrFactNo"
							onchange="getFactArea(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode"
							datatype="*" id="dwrFactArea">
								<option value="">請選擇廠別狀態</option>
						</select></td>
					</tr>
				</s:if>

				<tr>


					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" name="yymmdd"
						onClick="WdatePicker({maxDate:'%y-%M-{%d+2}',dateFmt:'yyyymmdd'})" class="Wdate" datatype="*" /> <input
						type="hidden" value="isnull" name="isnull" /></td>
					<td class="td_show_title">工作日/假日</td>
					<td class="td_input"><select name="ydata.workorholiday"
						id="workholiday" datatype="*" onchange="showRow()">
							<option value="">請選擇</option>
							<option value=0>工作日</option>
							<option value=1>假日</option>
							<option value=2>未排產</option>

					</select></td>

				</tr>
			</s:if>

			<s:if test="ydata!=null">

				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='ydata.id.factNo'/>"
							name="ydata.id.factNo" readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='ydata.id.factCode'/>"
							name="ydata.id.factCode" readonly /> </font>
							<input type="hidden" value="${loginUser.username}" name="ydata.usernameUd"/> <!--  修改者-->
						    <input type="hidden" value="<s:property value='ydata.dateCreate'/>" name="ydata.dateCreate"/><!-- 創建時間 -->
							</td>
				</tr>
				<tr>

					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" style="color:blue"
						id="yymm"
						value="<s:date name='ydata.id.yymmdd' format='yyyyMMdd'/>"
						name="yymmdd" readonly /> <input type="hidden" value="notnull"
						name="isnull" id="onModulus" /></td>
					<td class="td_show_title">工作日/假日</td>
					<td class="td_input"><select name="ydata.workorholiday"
						id="workholiday" datatype="*" onchange="showRow()">
							<option value="">請選擇</option>
							<s:if test="ydata.workorholiday==0">
								<option value=0 selected>工作日</option>
							</s:if>
							<s:else>
								<option value=0>工作日</option>
							</s:else>
							<s:if test="ydata.workorholiday==1">
								<option value=1 selected>假日</option>
							</s:if>
							<s:else>
								<option value=1>假日</option>
							</s:else>
							<s:if test="ydata.workorholiday==2">
								<option value=2 selected>未排產</option>
							</s:if>
							<s:else>
								<option value=2>未排產</option>
							</s:else>

					</select></td>

				</tr>

			</s:if>
			<tr>
				<td class="td_show_title" width="10%">人數(人)</td>

				<td class="td_input"><span id="personnum"><input
						id="renshu" type="text" name="ydata.personnum"
						value="<s:property value='%{formatDouble2(ydata.personnum)}'/>"
						datatype="*,*0-6">
				</span></td>
				<td class="td_show_title">上模數(模)</td>
				<td class="td_input"><span id="s_onModulus"><input
						id="shangmoshu" type="text" name="ydata.onModulus"
						datatype="*,*0-6"
						value="<s:property value='%{formatDouble2(ydata.onModulus)}'/>">
				</span></td>
			</tr>

			<tr>
				<td class="td_show_title">標準產量(模)</td>
				<td class="td_input"><span id="standardOutput"><input
						id="bzcl" type="text" name="ydata.standardOutput"
						value="<s:property value='%{formatDouble2(ydata.standardOutput)}'/>"
						datatype="*,*0-6">
				</span></td>

				<td class="td_show_title">實際產量(模)</td>
				<td class="td_input"><span id="actualYield"><input
						id="sjcl" type="text" name="ydata.actualYield"
						value="<s:property value='%{formatDouble2(ydata.actualYield)}'/>"
						datatype="*,*0-6">
				</span></td>

			</tr>
			
			<tr>
				<td class="td_show_title">實際生產雙數(雙)</td>
				<td class="td_input"><span id="actualpairs"><input
						id="in_actualpairs" type="text" name="ydata.actualpairs"
						value="<s:property value='%{formatDouble2(ydata.actualpairs)}'/>"
						datatype="*,*0-6">
				</span></td>

				<td class="td_show_title">客補生產雙數(雙)</td>
				<td class="td_input"><span id="hostpairs"><input
						id="in_hostpairs" type="text" name="ydata.hostpairs"
						value="<s:property value='%{formatDouble2(ydata.hostpairs)}'/>"
						datatype="*,*0-6">
				</span></td>

			</tr>
			<tr>
				<td class="td_show_title">廠補生產雙數(雙)</td>
				<td class="td_input"><span id="factpairs"><input
						id="in_factpairs" type="text" name="ydata.factpairs"
						value="<s:property value='%{formatDouble2(ydata.factpairs)}'/>"
						datatype="*,*0-6">
				</span></td>

				<td class="td_show_title">樣品生產雙數(雙)</td>
				<td class="td_input"><span id="samplepairs"><input
						id="in_samplepairs" type="text" name="ydata.samplepairs"
						value="<s:property value='%{formatDouble2(ydata.samplepairs)}'/>"
						datatype="*,*0-6">
				</span></td>

			</tr>
			<tr>
				<td class="td_show_title">出貨數(雙)</td>
				<td class="td_input"><span id="outnum"><input
						id="in_outnum" type="text" name="ydata.outnum"
						value="<s:property value='%{formatDouble2(ydata.outnum)}'/>"
						datatype="*,*0-6">
				</span></td>

				<td class="td_show_title">退貨數(雙)</td>
				<td class="td_input"><span id="backnum"><input
						id="in_backnum" type="text" name="ydata.backnum"
						value="<s:property value='%{formatDouble2(ydata.backnum)}'/>"
						datatype="*,*0-6">
				</span></td>

			</tr>
			<tr>
				<td class="td_show_title">天數(天)</td>
				<td class="td_input"><span id="daycount"><input
						id="tianshu" type="text" name="ydata.daycount"
						value="<s:property value='%{formatDouble2(ydata.daycount)}'/>"
						datatype="*,*0-7">
				</span> <input type="hidden"
					value="<s:property value='ydata.username'/>"
					name="ydata.username" />
				<!-- 添加用戶名 --></td>
				<td class="td_show_title">上模總工時(小時)</td>
				<td class="td_input"><span id="workhours"><input
						id="in_workhours" type="text" name="ydata.workhours"
						value="<s:property value='%{formatDouble2(ydata.workhours)}'/>"
						datatype="*,*0-6">
				</span></td>
			</tr>
		</table>

			
		<center>								
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;				 
			<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;				 						
			<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>		
		</center>
	</form>





</body>

</html>
