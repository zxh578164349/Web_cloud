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

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/validate.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" type="text/css" href="css/form.css" /> 
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="jquery/loding/ui.loading.css" />
<script type="text/javascript" src="jquery/loding/ui.loading.js"></script>
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
	<link rel="stylesheet" type="text/css" href="css/general_css.css" />
	<link href="tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />


<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form2").Validform({
			btnSubmit : "#sub2",
			tiptype : 3,
			showAllError : true,
			datatype : {
				"*0-12" : /^\d{0,2}$/
			}
		});
		demo.tipmsg.w["*0-12"] = "尾月不可以少於初月";

	});

	// 选择或者反选 checkbox  
	function CheckSelect(thisform) {
		var allfactno = document.getElementById("checkfactno");
		// 遍历 form  
		for ( var i = 0; i < thisform.elements.length; i++) {
			// 提取控件  
			var checkbox = thisform.elements[i];
			var span = document.getElementById("sp" + i);
			// 检查是否是指定的控件  
			if (checkbox.name === "factnolist" && checkbox.type === "checkbox"
					&& checkbox.checked === false && allfactno.checked === true) {
				// 正选  
				checkbox.checked = true;
				span.style.color = "red";

			} else if (checkbox.name === "factnolist"
					&& checkbox.type === "checkbox"
					&& checkbox.checked === true && allfactno.checked == false) {
				// 反选  
				checkbox.checked = false;
				span.style.color = "";

			}
		}
	}

	function CheckSelect2(thisform) {
		var allfactcode = document.getElementById("checkfactcode");
		for ( var i = 0; i < thisform.elements.length; i++) {
			var span = null;
			var checkbox = thisform.elements[i];
			if (navigator.userAgent.indexOf("MSIE") > 0) {
				span = document.getElementById("ssp" + (i - 1));
			} else {
				span = document.getElementById("ssp" + i);
			}

			if (checkbox.name === "factcodelist"
					&& checkbox.type === "checkbox"
					&& checkbox.checked === false
					&& allfactcode.checked === true) {
				checkbox.checked = true;
				span.style.color = "red";
			} else if (checkbox.name === "factcodelist"
					&& checkbox.type === "checkbox"
					&& checkbox.checked === true
					&& allfactcode.checked === false) {
				checkbox.checked = false;
				span.style.color = "";

			}
		}
	}

	function checkred(spid, cbid) {
		var span = document.getElementById(spid);
		var box = document.getElementById(cbid);
		if (box.checked == true) {
			span.style.color = "red";
		} else {
			span.style.color = "";
		}
	}

	function compare() {
		document.getElementById("hmonth").value = 0;
		var firth = Number(document.getElementById("fmonth").value);
		var last = Number(document.getElementById("lmonth").value);
		if (firth > last && last != "" && last != null) {
			document.getElementById("hmonth").value = 100;
		}

	}

	function clickone() {
		if (document.getElementById("one").checked == true) {
			document.getElementById("lmonth").disabled = "disabled";
		} else {
			document.getElementById("lmonth").disabled = "";
		}

	}

	function submis() {

		var jj = jQuery.noConflict();
		jQuery(function(jj) {

			jj(document).ui_loading({
				overlay : true,
				opacity : 0.2,
				supportIframe : true,
				message : '請稍後!正在查詢中..'
			});
		});
		var info = "";
		var info2 = "";
		var listfactNo = jj('[name = "factnolist"]:checkbox:checked');
		var listfactCode = jj('[name="factcodelist"]:checkbox:checked');
		for ( var i = 0; i < listfactNo.length; i++) {
			// 如果i+1等于选项长度则取值后添加空字符串，否则为逗号
			info = (info + listfactNo.get(i).value)
					+ (((i + 1) == listfactNo.length) ? '' : ',');
		}
		for ( var i = 0; i < listfactCode.length; i++) {
			info2 = (info2 + listfactCode.get(i).value)
					+ (((i + 1) == listfactCode.length) ? '' : ',');
		}

		var year = document.getElementById("nyear");
		var month = document.getElementById("fmonth");

		jj.ajax({
			type : "POST",
			//dataType : "html",
			url : "print2Y_print2Y",

			data : {
				year : year.value,
				month : month.value,
				factnolist : info,
				factcodelist : info2
			},
			contentType : "application/vnd.ms-excel",
			success : function(msg) {
				jj("#bodyid").html(msg);

			},
			error : function(xhr) {
				alert(xhr.responseText);
			}
		});
	}

	function changeinput() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			var mydiv = document.getElementById("printdiv");
			var input = document.createElement("input");
			input.id="showbtn";
			input.type = "submit";
			input.value = "顯示";
			input.onclick = function() {
				document.getElementById("hmonth").value = 0;
				document.getElementById("result").value = "inline;filename=";
				var firth = Number(document.getElementById("fmonth").value);
				var last = Number(document.getElementById("lmonth").value);
				if (firth > last && last != "") {
					document.getElementById("hmonth").value = 100;
				}
				//alert(document.getElementById("result").value);
			};
			mydiv.appendChild(input);
		}
	}

	function changeresult() {
		document.getElementById("hmonth").value = 0;
		document.getElementById("result").value = "attachment;filename=";
		var firth = Number(document.getElementById("fmonth").value);
		var last = Number(document.getElementById("lmonth").value);
		if (firth > last && last != "") {
			document.getElementById("hmonth").value = 100;
		}
	}
</script>
</head>

<body onload="changeinput()">

	<!--  <form action="print2Y_print2Y" method="post" id="form2" target="_blank"> -->
	<!--  <form action="print2Ypoi2003_print2Y" method="post" id="form2" target="_blank"> -->
	<form action="print2Ypoi_print2Y" method="post" id="form2"
		target="_blank">
		<table id="tb" >
			<caption>
				產量預估與產量資料下載				
			</caption>
			<s:if test="#session.factNo=='tw'">

				<tr>
					<td class="td_show_title">廠別</td>
					<td  width="6%"><span style="">全選</span>
						<input type="checkbox" value="all" id="checkfactno"
						onclick="CheckSelect(this.form)" style="width:18;height:18" /></td>
					<td class="td_input"><s:iterator value="#session.facts"
							status="x" id="temp">
							<span id="sp${x.index+1}">${temp[1]}(${temp[0]})</span>
							<input id="cb${x.index+1}" type="checkbox" value="${temp[0]}"
								name="factnolist" datatype="*"
								onclick="checkred('sp${x.index+1}','cb${x.index+1}')" />
						</s:iterator></td>
					<td rowspan="3" width="5%"><center>
							<div id="printdiv">
								<input type="button" value="下載" onclick="changeresult()"
									id="sub2" />&nbsp;&nbsp;&nbsp;
							</div>
						</center></td>
				</tr>


				<tr>
					<td class="td_show_title" width="5%">廠別狀態</td>
					<td ><span style="">全選</span> <input
						type="checkbox" value="all" id="checkfactcode"
						onclick="CheckSelect2(this.form)" style="width:18;height:18" /></td>
					<td class="td_input"><s:iterator value="#session.factcodes"
							id="temp" status="x">
							<span id="ssp${x.index+18}">${temp}</span>
							<input type="checkbox" value="${temp}" name="factcodelist"
								datatype="*" id="id${x.index+18}"
								onclick="checkred('ssp${x.index+18}','id${x.index+18}')" />
						</s:iterator><span style="color:blue">(提示:打印時自動篩選各廠所擁有的廠別狀態)</span></td>
				</tr>


			</s:if>

			<s:if test="#session.factNo!='tw'">

				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_show_title" width="6%"><span style="">全選</span>
						<input type="checkbox" value="all" id="checkfactno"
						onclick="CheckSelect(this.form)" style="width:18;height:18" /></td>
					<td class="td_input"><span id="sp1">${factName}(${factNo})</span><input
						id="cb1" type="checkbox" value="${factNo}" name="factnolist"
						datatype="*" onclick="checkred('sp1','cb1')" /></td>
					<td rowspan="3" width="5%"><center>
							<div id="printdiv">
								<input type="button" value="下載" onclick="changeresult()"
									id="sub2" />&nbsp;&nbsp;&nbsp;
							</div>
						</center>
					</td>
				</tr>

				<tr>
					<td class="td_show_title" width="5%">廠別狀態</td>
					<td class="td_show_title"><span style="">全選</span> <input
						type="checkbox" value="all" id="checkfactcode"
						onclick="CheckSelect2(this.form)" style="width:18;height:18" /></td>
					<td class="td_input"><s:iterator
							value="#session.factAreas_login" id="temp" status="x">
							<span id="ssp${x.index+4}">${temp}</span>
							<input type="checkbox" value="${temp}" name="factcodelist"
								datatype="*" id="id${x.index+4}"
								onclick="checkred('ssp${x.index+4}','id${x.index+4}')" />
						</s:iterator></td>
				</tr>

			</s:if>



			<tr>
				<td class="td_show_title">年月</td>
				<td class="td_input" colspan="2">年<span><select
						name="year" class="td_input" id="nyear" datatype="*">
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
					</select>
				</span> 月 <span><select name="month" class="td_input" id="fmonth"
						datatype="*">

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

					</select>
				</span> 至 <span> <select name="lmonth" class="td_input" id="lmonth"
						onchange="" disabled>

							<option value="" selected>請選擇月份</option>
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

					</select> </span> <input type="hidden" value="" id="hmonth" datatype="*0-12" /> <input
					type="hidden" value="" name="result" id="result" /> (單月打印<input
					type="radio" value="" id="one" checked onclick="clickone()"
					name="only" />&nbsp;多月打印<input type="radio" value="" id="more"
					onclick="clickone()" name="only" />) &nbsp;&nbsp;
					(Excel2007<input type="radio" value="Excel2007" checked
					name="type" />&nbsp;Excel2003<input type="radio" value="Excel2003"
					name="type" />)</td>
			</tr>

		</table>
	</form>

</body>
</html>
