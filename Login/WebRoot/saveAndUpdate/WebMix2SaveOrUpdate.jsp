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
			tipSweep : true,
			showAllError : true,
			datatype : {
				"*0-6" : /^-?\d{1,12}(\.[0-9]{1,3})?$/			
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					location.href="/Login/webmix2_getList";
				}else{
					alert(data.responseText);
				}				
			}
		});
		demo.tipmsg.w["*0-6"] = "只能數字且不超過12位數,可保留三位以內小數";
		demo.tipmsg.w["mus0-6"]="只能數字且不超過12位數，可保留三位以內小數";
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
          webmix2js.check(factno,factcode,yymmdd,function(x){
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
	 location.href="/Login/webmix2_getList3?backIndex=1";
 }           
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webmix2js.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>

<body>
	<form action="webmix2_addMix2" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">
			<caption>營收損益(每月輸入)</caption>
			<tbody id="tb_list_info">
				<tr></tr>
			</tbody>
			<tbody id="tb_list_info2">
				<s:if test="mix2==null">
					<tr>
						<td class="td_show_title"><span id="sfactNo2"></span>廠別</td>
						<td class="td_input"><s:if test="#session.factNo!='tw'">
								<input type="text" style="color:blue" name="mix2.id.factNo"
									value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span>
							</s:if> <s:if test="#session.factNo=='tw'">
								<select style="color:blue" name="mix2.id.factNo"
									onchange="getFactArea(this.value),check()" datatype="*" id="dwr_factno">
									<option value="">請選擇廠別</option>
									<s:iterator value="#session.facts" id="temp">
										<option value="${temp[0]}">${temp[1]
											}&nbsp;(${temp[0]})</option>
									</s:iterator>
								</select><span id="error1"></span>
							</s:if> <span id="sfactNo"></span>
						</td>
						<td class="td_show_title"><span id="syymm2"></span>年月</td>
						<td class="td_input"><input type="text" name="yymm" readonly="readonly"
							class="Wdate" onClick="WdatePicker();" id="dwr_yymmdd" onchange="check()" datatype="*"/><span id="error3"></span>
						</td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><s:if test="#session.factNo=='tw'">
								<select name="mix2.id.factCode" datatype="*" id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
								</select><span id="error2"></span>
							</s:if> <s:else>
								<select name="mix2.id.factCode" datatype="*" id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
									<s:iterator value="#session.factAreas_login" id="temp">
										<option value="${temp}">${temp}</option>
									</s:iterator>
								</select><span id="error2"></span>
							</s:else></td>
						<td class="td_show_title">請款雙數(雙)</td>
						<td class="td_input"><input type="text" name="mix2.invcount"
							value="<s:property value='mix2.invcount' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>
				<s:if test="mix2!=null">
					<input type="hidden" value="1" name="bs" />
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='mix2.id.factNo'/>"
								name="mix2.id.factNo" readonly /> </font></td>
						<td class="td_show_title">年月</td>
						<td class="td_input"><input type="text" style="color:blue"
							id="yymm" value="<s:date name='mix2.id.yymm' format='yyyyMM'/>"
							name="yymm" readonly />
							<input type="hidden" value="${loginUser.username}" name="mix2.usernameUd"/> <!--  修改者--></td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="mix2.id.factCode"
							datatype="*">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_other" id="temp">
									<s:if test="#session.temp==mix2.id.factCode">
										<option value="${temp}" selected>${temp}</option>
									</s:if>
									<s:else>
										<option value="${temp}">${temp}</option>
									</s:else>
								</s:iterator>
						</select>
						</td>
						<td class="td_show_title">請款雙數(雙)</td>
						<td class="td_input"><input type="text" name="mix2.invcount"
							value="<s:property value='mix2.invcount' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>
				<tr>
				    <td class="td_show_title">雙數請款金額(USD)</td>
					<td class="td_input"><input type="text" name="mix2.payPairs"
						value="<s:property value='mix2.payPairs' />" datatype="*0-6">
					</td>
					<td class="td_show_title">銷售收入(USD)</td>
					<td class="td_input"><input type="text" name="mix2.sellcount"
						value="<s:property value='mix2.sellcount' />" datatype="*0-6">
					</td>															
				</tr>
				<tr>
				    <td class="td_show_title">成本合計(USD)</td>
					<td class="td_input"><input type="text" name="mix2.costcount"
						value="<s:property value='mix2.costcount' />" datatype="*0-6">
					</td>
					<td class="td_show_title">直接應領薪資(USD)</td>
					<td class="td_input"><input type="text" name="mix2.wagezgUsd"
						value="<s:property value='mix2.wagezgUsd' />" datatype="*0-6" /></td>										
				</tr>
				<tr>
				    <td class="td_show_title">間接應領薪資(USD)</td>
					<td class="td_input"><input type="text" name="mix2.wagejgUsd"
						value="<s:property value='mix2.wagejgUsd' />" datatype="*0-6" /></td>
					<td class="td_show_title">直工加班費(USD)</td>
					<td class="td_input"><input type="text" name="mix2.addmoneyzg"
						value="<s:property value='mix2.addmoneyzg' />" datatype="*0-6">
					</td>
					
				</tr>
				<tr>
				    <td class="td_show_title">間工加班費(USD)</td>
					<td class="td_input"><input type="text" name="mix2.addmoneyjg"
						value="<s:property value='mix2.addmoneyjg'/>" datatype="*0-6"
						style="color:blue"></td>
					<td class="td_show_title">客補樣品請款雙數(雙)</td>
					<td class="td_input"><input type="text"
						name="mix2.hostinvcount"
						value="<s:property value='mix2.hostinvcount'/>" datatype="*0-6">
					</td>					
				</tr>
				<tr>
				    <td class="td_show_title">費用合計(USD)</td>
					<td class="td_input"><input type="text" name="mix2.cashcount"
						value="<s:property value='mix2.cashcount' />" datatype="*0-6">
					</td>
					<td class="td_show_title">其他(USD)</td>
					<td class="td_input"><input type="text" name="mix2.other"
						value="<s:property value='mix2.other'/>" datatype="*0-6">
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="mix2.username" />
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
