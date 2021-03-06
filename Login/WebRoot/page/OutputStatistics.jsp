<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" /> 
</head>

<body onload="changeinput()">	
	<form action="print2Ypoi_print2Y_hb" method="post" id="form2"
		target="_blank">		
		<table id="tb" class="table table-bordered table-condensed">
			
			<s:if test="#session.factNo=='tw'">

				<tr>
					<td class="td_show_title">廠別</td>
					<td  width="6%"><span style="">全選</span>
						<input type="checkbox" value="all" id="checkfactno"
						onclick="CheckSelect()" style="width:18;height:18" /></td>
					<td class="td_input"><s:iterator value="#session.facts"
							status="x" id="temp">
							<span id="sp${x.index}">${temp[1]}(${temp[0]})</span>
							<input id="cb${x.index}" type="checkbox" value="${temp[0]}"
								name="factnolist" datatype="*"
								onclick="checkred('sp${x.index}','cb${x.index}')" />
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
						onclick="CheckSelect2()" style="width:18;height:18" /></td>
					<td class="td_input"><s:iterator value="#session.factcodes"
							id="temp" status="x">
							<span id="ssp${x.index}">${temp}</span>
							<input type="checkbox" value="${temp}" name="factcodelist"
								 id="id${x.index}"
								onclick="checkred('ssp${x.index}','id${x.index}')" />
						</s:iterator></td>
				</tr>


			</s:if>

			<s:if test="#session.factNo!='tw'">

				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_show_title" width="6%"><span style="">全選</span>
						<input type="checkbox" value="all" id="checkfactno"
						onclick="CheckSelect()" style="width:18;height:18" /></td>
					<td class="td_input"><span id="sp0">${factName}(${factNo})</span><input
						id="cb0" type="checkbox" value="${factNo}" name="factnolist"
						datatype="*" onclick="checkred('sp0','cb0')" /></td>
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
						onclick="CheckSelect2()" style="width:18;height:18" /></td>
					<td class="td_input"><s:iterator
							value="#session.factAreas_login" id="temp" status="x">
							<span id="ssp${x.index}">${temp}</span>
							<input type="checkbox" value="${temp}" name="factcodelist"
								 id="id${x.index}"
								onclick="checkred('ssp${x.index}','id${x.index}')" />
						</s:iterator></td>
				</tr>

			</s:if>



			<tr>
				<td class="td_show_title">年月</td>
				
				<td class="td_input" colspan="2">				
					<span>
					<input type="text" name="sdate" onclick="WdatePicker({dateFmt:'yyyyMM',minDate:'%y-{%M-12}',maxDate:'#F{$dp.$D(\'lmonth\',{M:0})||\'%y-%M\'}'})" id="lmonth_1" class="Wdate" datatype="*"/>至					
					</span> 
					<span><input type="text" name="edate" onclick="WdatePicker({dateFmt:'yyyyMM',minDate:'#F{$dp.$D(\'lmonth_1\',{M:0})}',maxDate:'%y-%M'})" id="lmonth" class="Wdate" disabled/></span> 
					<input type="hidden" value="" id="hmonth" datatype="*0-12" /> <input
					type="hidden" value="" name="result" id="result" /> (單月打印<input
					type="radio" value="" id="one" checked onclick="clickone()"
					name="only" />&nbsp;多月打印<input type="radio" value="" id="more"
					onclick="clickone()" name="only" />) &nbsp;&nbsp;
					(Excel2003<input type="radio" value="Excel2003" checked
					name="type" />&nbsp;Excel2007<input type="radio" value="Excel2007"
					name="type" />)</td>
			</tr>

		</table>
	</form>

<script type="text/javascript">
	jq(function() {
		var demo = jq("#form2").Validform({
			btnSubmit : "#sub2",
			tiptype : 3,
			tipSweep : true,
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
		var factcodelist=document.getElementsByName("factnolist");
		// 遍历 form  
		for ( var i = 0; i < factcodelist.length; i++) {
			// 提取控件  
			var checkbox = factcodelist[i];
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

	function CheckSelect2() {
		var allfactcode = document.getElementById("checkfactcode");
		var factcodelist=document.getElementsByName("factcodelist");
		for ( var i = 0; i < factcodelist.length; i++) {
			var checkbox = factcodelist[i];
			
			/*if (navigator.userAgent.indexOf("MSIE") > 0) {
				span = document.getElementById("ssp" + (i - 1));
			} else {
				span = document.getElementById("ssp" + i);
			}*/
			//var span = document.getElementById("ssp" + i);
			var span=jq("#ssp"+i);

			if (checkbox.name === "factcodelist"
					&& checkbox.type === "checkbox"
					&& checkbox.checked === false
					&& allfactcode.checked === true) {
				checkbox.checked = true;
				//span.style.color = "red";
				span.css("color","red");
			} else if (checkbox.name === "factcodelist"
					&& checkbox.type === "checkbox"
					&& checkbox.checked === true
					&& allfactcode.checked === false) {
				checkbox.checked = false;
				//span.style.color = "";
				span.css("color","");

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
</body>
</html>
