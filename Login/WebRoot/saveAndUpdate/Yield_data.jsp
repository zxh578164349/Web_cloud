<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_data.jsp' starting page</title>

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
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
</head>
<script type="text/javascript">
	var jq=jQuery.noConflict();
	var loadi;
	jq(document).ajaxStart(function(){
		loadi=layer.load("正在提交,請稍等...");
	});
	jq(document).ajaxStop(function(){
		layer.close(loadi);
	});
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			ajaxPost:true,
			callback : function(data) {			    
				//document.getElementById("mydiv").style.display = "block";
				//form[0].submit();
				if(data=="0"){
					layer.msg("提交成功!",3,1);
				}else{
					alert(data.responseText);
				}				
			},
			datatype : {
				"*0-6" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
				"*1-6" : /^[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
				//"*1-6" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
				"*0-7" : /^-?\d{0,7}(\.[0-9]{1})?$/

			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		//demo.tipmsg.w["*1-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位以內小數";

	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});
	}

	function getFactArea2(mid) {
		document.getElementById("dwrFactArea2").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea2", x);
		});
	}

	function back() {
		
			layer.load("正在返回,請稍等...");
			window.location.href = "/Login/ydata_findPageBean3?backIndex=1";
		

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
			document.getElementById("onModulus").style.display = "none";
			document.getElementById("standardOutput").style.display = "none";
			document.getElementById("actualYield").style.display = "none";
			document.getElementById("daycount").style.display = "none";
			
			document.getElementById("actualpairs").style.display = "none";
			document.getElementById("hostpairs").style.display = "none";
			document.getElementById("factpairs").style.display = "none";
			document.getElementById("samplepairs").style.display = "none";
			document.getElementById("outnum").style.display = "none";
			document.getElementById("backnum").style.display = "none";
			document.getElementById("workhours").style.display = "none";
			
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
			document.getElementById("onModulus").style.display = "block";
			document.getElementById("standardOutput").style.display = "block";
			document.getElementById("actualYield").style.display = "block";
			document.getElementById("daycount").style.display = "block";
			
			document.getElementById("actualpairs").style.display = "block";
			document.getElementById("hostpairs").style.display = "block";
			document.getElementById("factpairs").style.display = "block";
			document.getElementById("samplepairs").style.display = "block";
			document.getElementById("outnum").style.display = "block";
			document.getElementById("backnum").style.display = "block";
			document.getElementById("workhours").style.display = "block";

		}

	}

	function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webydatejs.check(factno,factcode,yymmdd,function(x){
              if(x=="1"){
              alert("所選日期的前天數據還沒有輸入");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="已鎖定";
              document.getElementById("sub").style.color="red";
              document.getElementById("error1").innerHTML="<font color='color'>！</font>";
              document.getElementById("error2").innerHTML="<font color='color'>！</font>";
              document.getElementById("error3").innerHTML="<font color='color'>！</font>";
              }
              if(x=="2"){
              alert("數據庫已存在("+factno+factcode+yymmdd+")");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="已鎖定";
              document.getElementById("sub").style.color="red";
              document.getElementById("error1").innerHTML="<font color='color'>！</font>";
              document.getElementById("error2").innerHTML="<font color='color'>！</font>";
              document.getElementById("error3").innerHTML="<font color='color'>！</font>";
              }
              if(x=="0"){
              document.getElementById("sub").disabled=false;
              document.getElementById("sub").value="確定";
              document.getElementById("sub").style.color="white";
              document.getElementById("error1").innerHTML="";
              document.getElementById("error2").innerHTML="";
              document.getElementById("error3").innerHTML="";
              }               
          });               
       }                    
   }
   
/*禁止空格輸入*/
window.onload=function(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }  
        }       
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webydatejs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<body >

	<form action="ydata_addData" method="post" id="form">

		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="table1">
			<caption>產量資料</caption>
			<s:if test="ydata==null">

				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title" width="10%">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="ydata.id.factNo" value="${factNo}" readonly id="dwr_factno" /><span
							id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select><span id="error2"></span>
						</td>
					</tr>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><select style="color:blue"
							name="ydata.id.factNo" datatype="*" id="dwr_factno"
							onclick="getFactArea(this.value),check()">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span>
						</td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>
						</select><span id="error2"></span>
						</td>
					</tr>
				</s:if>

				<tr>


					<td class="td_show_title">日期</td>

					<td class="td_input"><input type="text" name="yymmdd"
						onClick="WdatePicker({maxDate:'%y-%M-{%d+2}'})" class="Wdate"
						datatype="*" id="yymmdd" onchange="check()" /><span id="error3"></span>
					</td>


					<td class="td_show_title">工作日/假日</td>
					<td class="td_input"><select name="ydata.workorholiday"
						id="workholiday" datatype="*" onclick="showRow()">
							<option value="">請選擇</option>
							<option value=0>工作日</option>
							<option value=1>假日</option>
							<option value=2>未排產</option>
					</select> <input type="hidden" value="isnull" name="isnull" /></td>

				</tr>
			</s:if>

			<s:if test="ydata!=null">

				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='ydata.id.factNo'/>"
							name="ydata.id.factNo" readonly /> </font>
					</td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='ydata.id.factCode'/>"
							name="ydata.id.factCode" readonly /> </font>
					</td>
				</tr>
				<tr>

					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" style="color:blue"
						id="yymm"
						value="<s:date name='ydata.id.yymmdd' format='yyyyMMdd'/>"
						name="yymmdd" readonly /> <input type="hidden" value="notnull"
						name="isnull" id="onModulus" />
					</td>
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

					</select>
					</td>
				</tr>
			</s:if>
			
<!-- 以下為隱藏部分 -->			
			
			<tr>
				<td class="td_show_title" width="10%">人數(人)</td>

				<td class="td_input"><span id="personnum"><input
						id="renshu" type="text" name="ydata.personnum"
						value="<s:property value='%{formatDouble2(ydata.personnum)}'/>"
						datatype="*,*0-6"> </span>
				</td>
				<td class="td_show_title">上模數(模)</td>
				<td class="td_input"><span id="onModulus"><input
						id="shangmoshu" type="text" name="ydata.onModulus" datatype="*0-6"
						value="<s:property value='%{formatDouble2(ydata.onModulus)}'/>">
				</span>
				</td>
			</tr>

			<tr>
				<td class="td_show_title">標準產量(模)</td>
				<td class="td_input"><span id="standardOutput"><input
						id="bzcl" type="text" name="ydata.standardOutput"
						value="<s:property value='%{formatDouble2(ydata.standardOutput)}'/>"
						datatype="*1-6"> </span>
				</td>

				<td class="td_show_title">實際產量(模)</td>
				<td class="td_input"><span id="actualYield"><input
						id="sjcl" type="text" name="ydata.actualYield"
						value="<s:property value='%{formatDouble2(ydata.actualYield)}'/>"
						datatype="*,*0-6"> </span>
				</td>

			</tr>


			<tr>
				<td class="td_show_title">實際生產雙數(雙)</td>
				<td class="td_input"><span id="actualpairs"><input
						id="in_actualpairs" type="text" name="ydata.actualpairs"
						value="<s:property value='%{formatDouble2(ydata.actualpairs)}'/>"
						datatype="*1-6"> </span>
				</td>

				<td class="td_show_title">客補生產雙數(雙)</td>
				<td class="td_input"><span id="hostpairs"><input
						id="in_hostpairs" type="text" name="ydata.hostpairs"
						value="<s:property value='%{formatDouble2(ydata.hostpairs)}'/>"
						datatype="*,*0-6"> </span>
				</td>

			</tr>
			<tr>
				<td class="td_show_title">廠補生產雙數(雙)</td>
				<td class="td_input"><span id="factpairs"><input
						id="in_factpairs" type="text" name="ydata.factpairs"
						value="<s:property value='%{formatDouble2(ydata.factpairs)}'/>"
						datatype="*,*0-6"> </span>
				</td>

				<td class="td_show_title">樣品生產雙數(雙)</td>
				<td class="td_input"><span id="samplepairs"><input
						id="in_samplepairs" type="text" name="ydata.samplepairs"
						value="<s:property value='%{formatDouble2(ydata.samplepairs)}'/>"
						datatype="*,*0-6"> </span>
				</td>

			</tr>
			<tr>
				<td class="td_show_title">出貨數(雙)</td>
				<td class="td_input"><span id="outnum"><input
						id="in_outnum" type="text" name="ydata.outnum"
						value="<s:property value='%{formatDouble2(ydata.outnum)}'/>"
						datatype="*,*0-6"> </span>
				</td>

				<td class="td_show_title">退貨數(雙)</td>
				<td class="td_input"><span id="backnum"><input
						id="in_backnum" type="text" name="ydata.backnum"
						value="<s:property value='%{formatDouble2(ydata.backnum)}'/>"
						datatype="*,*0-6"> </span>
				</td>

			</tr>
			<tr>
				<td class="td_show_title">天數(天)</td>
				<td class="td_input"><span id="daycount"><input
						id="tianshu" type="text" name="ydata.daycount"
						value="<s:property value='%{formatDouble2(ydata.daycount)}'/>"
						datatype="*,*0-7"> </span> <input type="hidden"
					value="<s:property value='#session.loginUser.username'/>"
					name="ydata.username" /> <input type="hidden" name="goadd"
					value="<%=request.getParameter("goadd")%>" id="goadd" /> <!-- 添加用戶名 -->
				</td>

				<td class="td_show_title">上模總工時(小時)</td>
				<td class="td_input"><span id="workhours"><input
						id="in_workhours" type="text" name="ydata.workhours"
						value="<s:property value='%{formatDouble2(ydata.workhours)}'/>"
						datatype="*,*0-6"> </span>
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

	<%--<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>

--%></body>

</html>
