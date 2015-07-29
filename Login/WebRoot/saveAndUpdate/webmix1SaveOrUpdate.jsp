<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

<title>My JSP 'Yield_prediction.jsp' starting page</title>

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
<style type="text/css">  
            input,select{  
                behavior: url(#default#savehistory);  
            }        
</style>
</head>
<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 3,
			tipSweep : true,
			showAllError : true,
			datatype : {
				"*0-9" : /^-?\d{0,9}(\.[0-9]{1,3})?$/,
				"*0-7" : /^-?\d{0,7}(\.[0-9])?$/

			}
		});
		demo.tipmsg.w["*0-9"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function back() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			location.href = "../webmix1_findPageBean";
		} else {
			location.href = "webmix1_findPageBean";
		}
	}
   function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webmix1js.check(factno,factcode,yymmdd,function(x){
              if(x==true){
              alert("數據庫已存在("+factno+factcode+yymmdd+")");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="已鎖定";
              document.getElementById("sub").style.color="red";
              document.getElementById("error1").innerHTML="<font color='color'>！</font>";
              document.getElementById("error2").innerHTML="<font color='color'>！</font>";
              document.getElementById("error3").innerHTML="<font color='color'>！</font>";
          }else{
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
	
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webmix1js.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="webmix1_add" method="post" id="form" >
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
			<s:if test="mix1==null">



				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="mix1.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="mix1.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select><span id="error2"></span></td>
					</tr>
				</s:if>


				<s:if test="#session.factNo=='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><select style="color:blue"
							name="mix1.id.factNo" datatype="*"
							onchange="getFactArea(this.value),check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="mix1.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>

						</select><span id="error2"></span></td>
					</tr>
				</s:if>


				<tr>
					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" name="yymmdd"
						onClick="WdatePicker()" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()" value=""/><span id="error3"></span></td>
					<td class="td_show_title">上班天數</td>
					<td class="td_input"><input type="text" name="mix1.workday"
						value="<s:property value='mix1.workday' />" datatype="*,*0-9">

						<input type="hidden" value="isnull" name="isnull" /></td>

				</tr>
			</s:if>

			<s:if test="mix1!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='mix1.id.factNo'/>"
							name="mix1.id.factNo" readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='mix1.id.factCode'/>"
							name="mix1.id.factCode" readonly /> </font></td>
				</tr>
				<tr>
					<td class="td_show_title">年月</td>

					<td class="td_input"><input type="text" style="color:blue"
						id="yymm"
						value="<s:date name='mix1.id.yymmdd' format='yyyyMMdd'/>"
						name="yymmdd" readonly /></td>
					<td class="td_show_title">上班天數</td>
					<td class="td_input"><input type="text" name="mix1.workday"
						value="<s:property value='mix1.workday' />" datatype="*,*0-9">

						<input type="hidden" value="notnull" name="isnull" /></td>
				</tr>

			</s:if>


			<tr>
				<td class="td_show_title">當日上模人數</td>
				<td class="td_input"><input type="text" name="mix1.everypeople"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.everypeople)}' />">
				</td>
				<td class="td_show_title">當日上模數</td>
				<td class="td_input"><input type="text" name="mix1.everydemo"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.everydemo)}' />">
				</td>


			</tr>
			<tr>
				<td class="td_show_title">標准生產模數</td>
				<td class="td_input"><input type="text"
					name="mix1.standarddemo" datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.standarddemo)}' />">
				</td>
				<td class="td_show_title">實際生產模數</td>
				<td class="td_input"><input type="text" name="mix1.actualdemo"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.actualdemo)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">實際生產雙數</td>
				<td class="td_input"><input type="text" name="mix1.actualpairs"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.actualpairs)}' />">
				</td>
				<td class="td_show_title">客補生產雙數</td>
				<td class="td_input"><input type="text" name="mix1.hostpairs"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.hostpairs)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">廠補生產雙數</td>
				<td class="td_input"><input type="text" name="mix1.factpairs"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.factpairs)}' />">

					<input type="hidden"
					value="<s:property value='#session.loginUser.username'/>"
					name="mix1.username" /> <!-- 添加用戶名 --></td>
				<td class="td_show_title">樣品生產雙數</td>
				<td class="td_input"><input type="text" name="mix1.samplepairs"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.samplepairs)}' />">
				</td>
			</tr>

			<tr>
				<td class="td_show_title">出貨數</td>
				<td class="td_input"><input type="text" name="mix1.outnum"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.outnum)}' />">
				</td>
				<td class="td_show_title">退貨數</td>
				<td class="td_input"><input type="text" name="mix1.backnum"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(mix1.backnum)}' />">

				</td>

			</tr>

		</table>
		<center>
			<input  type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;
			<s:if test="mix1!=null">
				<input type="button" value="返回" id="btn_back"
					onclick="javascript:location.href='webmix1_findPageBean'" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</s:if>
			<s:else>
				<input type="button" value="返回" onclick="back()" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</s:else>
		</center>
	</form>

</body>
</html>
