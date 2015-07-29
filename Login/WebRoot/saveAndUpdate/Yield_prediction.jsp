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
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>

</head>
<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 3,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-8" : /^\d{0,8}(\.[0-9]{1,3})?$/,
				"*0-7" : /^\d{0,7}(\.[0-9])?$/

			}
		});
		demo.tipmsg.w["*0-8"] = "只能數字且不超過8位數,可保留三位以內小數";
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
			location.href = "../ypre_findPageBean";
		} else {
			location.href = "ypre_findPageBean";
		}
	}
	function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webyprejs.check(factno,factcode,yymmdd,function(x){
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
<script type='text/javascript' src='/Login/dwr/interface/webyprejs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="ypre_addYPre" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
		   <caption>產量預估</caption>
  			<s:if test="pre==null">
				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="pre.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="pre.id.factCode"
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
							name="pre.id.factNo" datatype="*" id="dwr_factno"
							onchange="getFactArea(this.value),check()">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="pre.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>

						</select><span id="error2"></span></td>
					</tr>
				</s:if>


				<tr>
					<td class="td_show_title">年月</td>
					<td class="td_input">
						<input type="text" name="yymm"
						onClick="WdatePicker()" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()"/>
						<span id="error3"></span></td>
					<td class="td_show_title">戰力分析模數</td>
					<td class="td_input"><input type="text"
						name="pre.forceAnalysis" datatype="*,*0-7">
						<input type="hidden" value="isnull" name="isnull" />
						</td>

				</tr>
			</s:if>

			<s:if test="pre!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='pre.id.factNo'/>" name="pre.id.factNo"
							readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='pre.id.factCode'/>"
							name="pre.id.factCode" readonly /> </font></td>
				</tr>
				<tr>
					<td class="td_show_title">年月</td>
					
					<td class="td_input"><input type="text" style="color:blue"
						id="yymm" value="<s:date name='pre.id.yymm' format='yyyyMM'/>"
						name="yymm" readonly /> 
					</td>
					<td class="td_show_title">戰力分析模數</td>
					<td class="td_input"><input type="text"
						name="pre.forceAnalysis"
						value="<s:property value='pre.forceAnalysis' />" datatype="*,*0-7">
						<input type="hidden" value="notnull" name="isnull" />
					</td>
				</tr>

			</s:if>


			<tr>
				<td class="td_show_title">預計生產模數</td>
				<td class="td_input"><input type="text"
					name="pre.expectedProduction" datatype="*,*0-7"
					value="<s:property value='%{formatDouble2(pre.expectedProduction)}' />">
				</td>
				<td class="td_show_title">預計請款雙數</td>
				<td class="td_input"><input type="text"
					name="pre.expectedPayment" datatype="*,*0-7"
					value="<s:property value='%{formatDouble2(pre.expectedPayment)}' />">
				</td>


			</tr>
			<tr>
				<td class="td_show_title">總機孔</td>
				<td class="td_input"><input type="text" name="pre.hole"
					datatype="*,*0-8"
					value="<s:property value='%{formatDouble2(pre.hole)}' />">
				</td>
				<td class="td_show_title">有效孔位</td>
				<td class="td_input"><input type="text"
					name="pre.positiveNumber" datatype="*,*0-8"
					value="<s:property value='%{formatDouble2(pre.positiveNumber)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">工程/樣品</td>
				<td class="td_input"><input type="text" name="pre.sample"
					datatype="*,*0-8"
					value="<s:property value='%{formatDouble2(pre.sample)}' />">
				</td>
				<td class="td_show_title">補料孔位</td>
				<td class="td_input"><input type="text" name="pre.accessories"
					datatype="*,*0-8"
					value="<s:property value='%{formatDouble2(pre.accessories)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">其他</td>
				<td class="td_input"><input type="text" name="pre.other"
					datatype="*,*0-8"
					value="<s:property value='%{formatDouble2(pre.other)}' />">

					<input type="hidden"
					value="<s:property value='#session.loginUser.username'/>"
					name="pre.username" /> <!-- 添加用戶名 --></td>
			</tr>

		</table>
		<center>
			<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;
			<s:if test="pre!=null">
				<input type="button" value="返回"
					onclick="javascript:location.href='ypre_findPageBean'" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</s:if>
			<s:else>
				<input type="button" value="返回" onclick="back()" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
			</s:else>

		</center>
	</form>

</body>
</html>
