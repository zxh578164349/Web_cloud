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

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-9" : /^\d{1,9}(\.[0-9]{1,3})?$/,
				"*0-7" : /^\d{1,7}(\.[0-9])?$/
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
		demo.tipmsg.w["*0-9"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function back() {
		    layer.load("正在返回,請稍等...");
			location.href = "/Login/webpersonnum_findPageBean3?backIndex=1";
		
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webpersonnumjs.check(factno,factcode,yymmdd,function(x){
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
<script type='text/javascript' src='/Login/dwr/interface/webpersonnumjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="webpersonnum_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
		  <caption>人員考勤</caption>
			<s:if test="person==null">
				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="person.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="person.id.factCode"
							datatype="*" onchange="check()" id="dwrFactArea">
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
							name="person.id.factNo" datatype="*"
							onchange="getFactArea(this.value),check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="person.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>

						</select><span id="error2"></span></td>
					</tr>
				</s:if>


				<tr>
					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" name="yymm"
						onClick="WdatePicker()" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span></td>
					<td class="td_show_title">直工應到人數(人)</td>
					<td class="td_input"><input type="text"
						name="person.standardnumzg"
						value="<s:property value='person.standardnumzg' />"
						datatype="*,*0-9"> <input type="hidden" value="isnull"
						name="isnull" /></td>

				</tr>
			</s:if>

			<s:if test="person!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='person.id.factNo'/>"
							name="person.id.factNo" readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='person.id.factCode'/>"
							name="person.id.factCode" readonly /> </font></td>
				</tr>
				<tr>
					<td class="td_show_title">日期</td>

					<td class="td_input"><input type="text" style="color:blue"
						id="yymm"
						value="<s:date name='person.id.yymmdd' format='yyyyMMdd'/>"
						name="yymm" readonly /></td>
					<td class="td_show_title">直工應到人數(人)</td>
					<td class="td_input"><input type="text"
						name="person.standardnumzg"
						value="<s:property value='person.standardnumzg' />"
						datatype="*,*0-9"> <input type="hidden" value="notnull"
						name="isnull" id="onModulus" /></td>
				</tr>

			</s:if>


			<tr>
				<td class="td_show_title">直工實到人數(人)</td>
				<td class="td_input"><input type="text" name="person.realnumzg"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(person.realnumzg)}' />">
				</td>
				<td class="td_show_title">間工應到人數(人)</td>
				<td class="td_input"><input type="text"
					name="person.standardnumjg" datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(person.standardnumjg)}' />">
				</td>


			</tr>
			<tr>
				<td class="td_show_title">間工實到人數(人)</td>
				<td class="td_input"><input type="text" name="person.realnumjg"
					datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(person.realnumjg)}' />">
				</td>
				<td class="td_show_title">外掛編應到人數(人)</td>
				<td class="td_input"><input type="text"
					name="person.outstandardnum" datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(person.outstandardnum)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">外掛編實到人數(人)</td>
				<td class="td_input"><input type="text"
					name="person.outrealnum" datatype="*,*0-9"
					value="<s:property value='%{formatDouble2(person.outrealnum)}' />">

					<input type="hidden"
					value="<s:property value='#session.loginUser.username'/>"
					name="person.username" /> <!-- 添加用戶名 --></td>


			</tr>

		</table>
		<center>
			<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;			
				<input type="button" value="返回" onclick="back()" id="btn_back" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>

		</center>
	</form>

</body>
</html>
