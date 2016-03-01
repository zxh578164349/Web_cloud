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
<script type="text/javascript" src="jquery/DatePicker/WdatePicker.js"></script>
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
			tipSweep:true,
			datatype : {
				"9_1" : /^-?\d{1,9}(\.[0-9]{1})?$/,
				"9_2" : /^-?\d{1,9}(\.[0-9]{1,2})?$/,
				"9_4" : /^-?\d{1,9}(\.[0-9]{1,4})?$/,
				"my9_4" : /^-?\d{1,9}(\.[0-9]{4})$/		
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					location.href="/Login/kpifact_findPageBean";
				}
				if(data=="1"){
					alert(data.responseText);
				}
			}
		});
		demo.tipmsg.w["9_1"] = "只能數字且不超過9位數,可保留一位以內小數";
		demo.tipmsg.w["9_2"] = "只能數字且不超過9位數,可保留兩位小數";
		demo.tipmsg.w["9_4"] = "只能數字且不超過9位數,可保留四位小數";
		demo.tipmsg.w["my9_4"]="只能數字且不超過9位數,和精確到四位小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function back() {
		    layer.load("正在返回,請稍等...");
			location.href = "/Login/kpifact_findPageBean3?backIndex=1";							
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          kpifactjs.findById(factno,factcode,yymmdd,function(x){
              if(x!=null){
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
<script type='text/javascript' src='/Login/dwr/interface/webcostjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kpifactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="kpifact_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
		　　<caption>KPI年度目標</caption>
			<s:if test="kpi==null">
				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="kpi.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="kpi.id.factCode"
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
							name="kpi.id.factNo" datatype="*"
							onchange="getFactArea(this.value),check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="kpi.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>

						</select><span id="error2"></span></td>
					</tr>
				</s:if>


				<tr>
					<td class="td_show_title">年份</td>
					<td class="td_input"><input type="text" name="kpi.id.yyyy"
						onClick="WdatePicker({dateFmt:'yyyyMM'})" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span></td>
					<td class="td_show_title">當月產量(模)</td>
					<td class="td_input"><input type="text" name="kpi.thisYield"
						value="<s:property value='kpi.thisYield' />" datatype="9_1">
					</td>

				</tr>
			</s:if>

			<s:if test="kpi!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='kpi.id.factNo'/>"
							name="kpi.id.factNo" readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='kpi.id.factCode'/>"
							name="kpi.id.factCode" readonly /> </font>
							<input type="hidden" value="${loginUser.username}" name="kpi.usernameUd"/> <!--  修改者-->
							</td>
				</tr>
				<tr>
					<td class="td_show_title">年份</td>

					<td class="td_input"><input type="text" style="color:blue"
						id="yymm" value="<s:property value='kpi.id.yyyy'/>"
						name="kpi.id.yyyy" readonly /></td>
					<td class="td_show_title">當月產量(模)</td>
					<td class="td_input"><input type="text" name="kpi.thisYield"
						value="<s:property value='kpi.thisYield' />" datatype="9_1">

						<input type="hidden" value="notnull" name="isnull" id="onModulus" />
					</td>
				</tr>

			</s:if>


			<tr>
				<td class="td_show_title">月均回轉(回)</td>
				<td class="td_input"><input type="text"
					name="kpi.avgCircle" datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.avgCircle)}' />">
				</td>
				<td class="td_show_title">時迴轉(模/H)</td>
				<td class="td_input"><input type="text"
					name="kpi.avgCirclehour" datatype="9_1"
					value="<s:property value='kpi.avgCirclehour' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">廠補率</td>
				<td class="td_input"><input type="text" name="kpi.factaddRate"
					datatype="9_4"
					value="<s:property value='kpi.factaddRate' />">
				</td>
				<td class="td_show_title">產能達成率</td>
				<td class="td_input"><input type="text" name="kpi.productRate"
					datatype="9_4"
					value="<s:property value='kpi.productRate' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">成倉庫存(雙)</td>
				<td class="td_input"><input type="text" name="kpi.storeNum"
					datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.storeNum)}' />">
				</td>
				<td class="td_show_title">已出未請(雙)</td>
				<td class="td_input"><input type="text" name="kpi.outRequest"
					datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.outRequest)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">生產與請款差異率</td>
				<td class="td_input"><input type="text"
					name="kpi.outrequestRate" datatype="9_4"
					value="<s:property value='kpi.outrequestRate' />">
				</td>
				<td class="td_show_title">全廠人均時產能(模/H)</td>
				<td class="td_input"><input type="text" name="kpi.avgFactpro"
					datatype="9_4"
					value="<s:property value='kpi.avgFactpro' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">直工人均产能(模/人)</td>
				<td class="td_input"><input type="text"
					name="kpi.avgZgpro" datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.avgZgpro)}' />">


				</td>
				<td class="td_show_title">全厂人均产能(模/人)</td>
				<td class="td_input"><input type="text" name="kpi.avgPerpro"
					datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.avgPerpro)}' />">
				</td>
			</tr>

			<tr>
				<td class="td_show_title">人均產值(USD/人)</td>
				<td class="td_input"><input type="text" name="kpi.avgPermoney"
					datatype="9_4"
					value="<s:property value='kpi.avgPermoney' />">
				</td>
				<td class="td_show_title">人薪產值</td>
				<td class="td_input"><input type="text" name="kpi.permoney"
					datatype="9_4"
					value="<s:property value='kpi.permoney' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">水用量单耗(噸/模)</td>
				<td class="td_input"><input type="text" name="kpi.waterTon"
					datatype="my9_4"
					value="<s:property value='kpi.waterTon' />">
				</td>
				<td class="td_show_title">电度数单耗(度/模)</td>
				<td class="td_input"><input type="text" name="kpi.lightDu"
					datatype="my9_4"
					value="<s:property value='kpi.lightDu' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">蒸汽单耗(噸/模)</td>
				<td class="td_input"><input type="text" name="kpi.gasUsd"
					datatype="my9_4"
					value="<s:property value='kpi.gasUsd' />">
				</td>
				<td class="td_show_title">修繕單耗(USD/模)</td>
				<td class="td_input"><input type="text" name="kpi.wasteUsd"
					datatype="my9_4"
					value="<s:property value='kpi.wasteUsd' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">主材料成本比率</td>
				<td class="td_input"><input type="text" name="kpi.mainRate"
					datatype="9_4"
					value="<s:property value='kpi.mainRate' />">
				</td> 
				<td class="td_show_title">邊料率</td>
				<td class="td_input"><input type="text" name="kpi.sideRate"
					datatype="9_4"
					value="<s:property value='kpi.sideRate' />">
				</td>	              
			</tr>
			<tr>
				<td class="td_show_title">報廢率</td>
				<td class="td_input"><input type="text" name="kpi.wasteRate"
					datatype="9_4"
					value="<s:property value='kpi.wasteRate' />">
				</td>
				<td class="td_show_title">全廠總損耗</td>
				<td class="td_input"><input type="text" name="kpi.wasteFact"
					datatype="9_4"
					value="<s:property value='kpi.wasteFact' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">無形損耗</td>
				<td class="td_input"><input type="text" name="kpi.wasteNo"
					datatype="9_4"
					value="<s:property value='kpi.wasteNo' />">
				</td>
				<td class="td_show_title">直間比</td>
				<td class="td_input"><input type="text" name="kpi.zjRate"
					datatype="9_4"
					value="<s:property value='kpi.zjRate' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">工傷件數(件)</td>
				<td class="td_input"><input type="text" name="kpi.hurtNum"
					datatype="9_4"
					value="<s:property value='kpi.hurtNum' />">
				</td>
				<td class="td_show_title">直工離職率</td>
				<td class="td_input"><input type="text" name="kpi.zgleaveRate"
					datatype="9_4"
					value="<s:property value='kpi.zgleaveRate' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">全廠離職率</td>
				<td class="td_input"><input type="text" name="kpi.factleaveRate"
					datatype="9_4"
					value="<s:property value='kpi.factleaveRate' />">
					<input type="hidden"
					value="<s:property value='#session.loginUser.username'/>"
					name="kpi.username" /> <!-- 添加用戶名 -->
				</td>
				
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
