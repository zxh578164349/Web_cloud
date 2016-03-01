<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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

<title>My JSP 'WebMixPersonSaveOrUpdate.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/form.css" />
<link rel="stylesheet" type="text/css" href="css/button_css.css" />
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript" src="jquery/layer/layer.min.js"></script>
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
			tipSweep : true,
			datatype : {
				"*0-6" : /^\d{1,12}(\.[0-9]{1,3})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					location.href="/Login/webmixPerson_getList";
				}else{
					alert(data.responseText);
				}				
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過12位數,可保留三位以內小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});
	}
	
	   function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webmixpersonjs.check(factno,factcode,yymmdd,function(x){
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
function back(){
	layer.load("正在返回,請稍等...");
	location.href="/Login/webmixPerson_getList3?backIndex=1";
}
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webmixpersonjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>

<body>
	<form action="webmixPerson_addMixPerson" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">
			 <caption>人數工時(每月輸入)</caption>
			<tbody id="tb_list_info">
				<tr></tr>
			</tbody>
			<tbody id="tb_list_info2">
				<s:if test="mixperson==null">
					<tr>
						<td class="td_show_title"><span id="sfactNo2"></span>廠別</td>
						<td class="td_input"><s:if test="#session.factNo!='tw'">
								<input type="text" style="color:blue" name="mixperson.id.factNo" value="${factNo}"
									readonly id="dwr_factno"/><span id="error1"></span>
							</s:if> <s:if test="#session.factNo=='tw'">
								<select style="color:blue" name="mixperson.id.factNo"
									onchange="getFactArea(this.value),check()" datatype="*" id="dwr_factno">
									<option value="">請選擇廠別</option>
									<s:iterator value="#session.facts" id="temp">
										<option value="${temp[0]}">${temp[1]
											}&nbsp;(${temp[0]})</option>
									</s:iterator>
								</select><span id="error1"></span>
							</s:if> </td>
						<td class="td_show_title"><span id="syymm2"></span>年月</td>
						<td class="td_input"><input type="text" name="yymm" readonly="readonly"
							class="Wdate" onClick="WdatePicker();" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span>
							</td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><s:if test="#session.factNo=='tw'">
								<select name="mixperson.id.factCode" datatype="*"
									id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
								</select><span id="error2"></span>
							</s:if> <s:else>
								<select name="mixperson.id.factCode" datatype="*" id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
									<s:iterator value="#session.factAreas_login" id="temp" >
										<option value="${temp}">${temp}</option>
									</s:iterator>
								</select><span id="error2"></span>
							</s:else>
						</td>
						<td class="td_show_title">直工發薪人數(人)</td>
						<td class="td_input"><input type="text"
							name="mixperson.personzg"
							value="<s:property value='mixperson.personzg' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>
				<s:if test="mixperson!=null">
					<input type="hidden" value="1" name="bs" />
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='mixperson.id.factNo'/>"
								name="mixperson.id.factNo" readonly /> </font>
						</td>
						<td class="td_show_title">年月</td>
						<td class="td_input"><input type="text" style="color:blue"
							id="yymm" name="yymm"
							value="<s:date name='mixperson.id.yymm' format='yyyyMM'/>"
							readonly />
							<input type="hidden" value="${loginUser.username}" name="mixperson.usernameUd"/> <!--  修改者-->
						</td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="mixperson.id.factCode"
							datatype="*">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_other" id="temp">
									<s:if test="#session.temp==mixperson.id.factCode">
										<option value="${temp}" selected>${temp}</option>
									</s:if>
									<s:else>
										<option value="${temp}">${temp}</option>
									</s:else>
								</s:iterator>
						</select></td>
						<td class="td_show_title">直工發薪人數(人)</td>
						<td class="td_input"><input type="text"
							name="mixperson.personzg"
							value="<s:property value='mixperson.personzg' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>

				<tr>
					<td class="td_show_title">間工發薪人數(人)</td>
					<td class="td_input"><input type="text"
						name="mixperson.personjg"
						value="<s:property value='mixperson.personjg' />" datatype="*0-6">
					</td>
					<td class="td_show_title">直工正班工時(小時)</td>
					<td class="td_input"><input type="text"
						name="mixperson.timezg"
						value="<s:property value='mixperson.timezg' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">間工正班工時(小時)</td>
					<td class="td_input"><input type="text"
						name="mixperson.timejg"
						value="<s:property value='mixperson.timejg' />" datatype="*0-6" />
					</td>
					<td class="td_show_title">工傷件數(件)</td>
					<td class="td_input"><input type="text"
						name="mixperson.hurtnum"
						value="<s:property value='mixperson.hurtnum' />" datatype="*0-6" />
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">直工離職人數(人)</td>
					<td class="td_input"><input type="text"
						name="mixperson.leavenumbzg"
						value="<s:property value='mixperson.leavenumbzg' />"
						datatype="*0-6" />
					</td>
					<td class="td_show_title">間工離職人數(人)</td>
					<td class="td_input"><input type="text"
						name="mixperson.leavenumjg"
						value="<s:property value="mixperson.leavenumjg"/>" datatype="*0-6"
						>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">直工加班工時(小時)</td>
					<td class="td_input"><input type="text"
						name="mixperson.addtimezg"
						value="<s:property value='mixperson.addtimezg' />" datatype="*0-6" />
					</td>
					<td class="td_show_title">間工加班工時(小時)</td>
					<td class="td_input"><input type="text"
						name="mixperson.addtimejg"
						value="<s:property value="mixperson.addtimejg"/>" datatype="*0-6"
						/>
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="mixperson.username" />
					</td>
				</tr>			
			</tbody>
		</table>
		<center>
			<input type="button" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; <input
				type="button" value="返回" id="btn_back"
				onclick="javascript:back()" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>

		</center>
	</form>
</body>
</html>
