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
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>

<body>
	<form action="webformula_addformula" method="post" id="form">
		<table class="table table-condensed">			  			
			<tbody id="tb_list_info2">
				<s:if test="formula==null">
					<tr>
						<td class="td_show_title"><span id="sfactNo2"></span>廠別</td>
						<td class="td_input"><s:if test="#session.factNo!='tw'">
								<input type="text" style="color:blue" name="formula.id.factNo"
									value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span>
							</s:if> <s:if test="#session.factNo=='tw'">
								<select style="color:blue" name="formula.id.factNo"
									onchange="getFactArea(this.value),check()" datatype="*" id="dwr_factno">
									<option value="">請選擇廠別</option>
									<s:iterator value="#session.facts" id="temp">
										<option value="${temp[0]}">${temp[1]
											}&nbsp;(${temp[0]})</option>
									</s:iterator>
								</select><span id="error1"></span>
							</s:if> <span id="sfactNo"></span></td>
						<td class="td_show_title"><span id="syymm2"></span>年月</td>
						<td class="td_input"><input type="text" name="yymm" readonly="readonly"
							class="Wdate" onClick="WdatePicker();" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span></td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><s:if test="#session.factNo=='tw'">
								<select name="formula.id.factCode" datatype="*" id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
								</select><span id="error2"></span>
							</s:if> <s:else>
								<select name="formula.id.factCode" datatype="*" id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
									<s:iterator value="#session.factAreas_login" id="temp">
										<option value="${temp}">${temp}</option>
									</s:iterator>
								</select><span id="error2"></span>
							</s:else>
						</td>
						<td class="td_show_title">用水量(噸)</td>
						<td class="td_input"><input type="text" name="formula.waterton"
							value="<s:property value='formula.waterton' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>
				<s:if test="formula!=null">
					<input type="hidden" value="1" name="bs" />
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='formula.id.factNo'/>" name="formula.id.factNo"
								readonly /> </font>
						</td>
						<td class="td_show_title">年月</td>
						<td class="td_input"><input type="text" style="color:blue"
							id="yymm" value="<s:date name='formula.id.yymm' format='yyyyMM'/>"
							name="yymm" readonly />
							<input type="hidden" value="${loginUser.username}" name="formula.usernameUd"/> <!--  修改者-->
						</td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input">
						<input type="text" value="<s:property value='formula.id.factCode'/>" name="formula.id.factCode" style="color:blue" readonly/>						
						</td>
						<td class="td_show_title">用水量(噸)</td>
						<td class="td_input"><input type="text" name="formula.waterton"
							value="<s:property value='formula.waterton' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>

				<tr>
					<td class="td_show_title">用水金額(USD)</td>
					<td class="td_input"><input type="text" name="formula.waterusd"
						value="<s:property value='formula.waterusd' />" datatype="*0-6">
					</td>
					<td class="td_show_title">用電量(度)</td>
					<td class="td_input"><input type="text" name="formula.electricdu"
						value="<s:property value='formula.electricdu' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">用電金額(USD)</td>
					<td class="td_input"><input type="text" name="formula.electricusd"
						value="<s:property value='formula.electricusd' />" datatype="*0-6">
					</td>
					<td class="td_show_title">用蒸汽量(噸)</td>
					<td class="td_input"><input type="text" name="formula.gaston"
						value="<s:property value='formula.gaston' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">用蒸汽金額(USD)</td>
					<td class="td_input"><input type="text" name="formula.gasusd"
						value="<s:property value='formula.gasusd' />" datatype="*0-6">
					</td>
					<td class="td_show_title">用柴油量(噸)</td>
					<td class="td_input"><input type="text" name="formula.oilton"
						value="<s:property value='formula.oilton' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">用柴油金額(USD)</td>
					<td class="td_input"><input type="text" name="formula.oilusd"
						value="<s:property value='formula.oilusd' />" datatype="*0-6" />						
					</td>
					<td class="td_show_title">修繕金額(USD)</td>
					<td class="td_input"><input type="text" name="formula.repiarMoney"
						value="<s:property value='formula.repiarMoney' />" datatype="*0-6" />
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="formula.username" />
					</td>
					
				</tr>
			</tbody>
		</table>
		<center>
			<input type="button" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			 
				<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 			
				<input type="button" value="返回" id="btn_back"
				onclick="javascript:back()" class="btn btn-primary"/>
		</center>
	</form>
	
<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-6" : /^-?\d{1,12}(\.[0-9]{1,3})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="/Login/webformula_getList";
					loadUrl("/Login/webformula_getList");
				}else{
					//alert(data.responseText);
					layer.msg("提交失敗",3,3);
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
          webformulajs.check(factno,factcode,yymmdd,function(x){
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
jq(function(){
	goTrim();
});
function back(){	
	loadUrl("/Login/webformula_getList3?backIndex=1");
}             
</script>
</body>
</html>
