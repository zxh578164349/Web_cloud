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
			datatype : {
				"*0-9" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
				"*0-7" : /^-?\d{1,7}(\.[0-9])?$/
			},
			ajaxPost:true,
			callback:function(data){				
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					location.href="/Login/webcost_findPageBean";
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
			location.href = "/Login/webcost_findPageBean";
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webcostjs.check(factno,factcode,yymmdd,function(x){
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
<script type='text/javascript' src='/Login/dwr/interface/webcostjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="webcost_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0">
		　　<caption>資材資料</caption>
			<s:if test="cost==null">



				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="cost.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="cost.id.factCode"
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
							name="cost.id.factNo" datatype="*"
							onchange="getFactArea(this.value),check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="cost.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>

						</select><span id="error2"></span></td>
					</tr>
				</s:if>


				<tr>
					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" name="yymm"
						onClick="WdatePicker()" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span></td>
					<td class="td_show_title">當月實際耗料(KG)</td>
					<td class="td_input"><input type="text" name="cost.actlost"
						value="<s:property value='cost.actlost' />" datatype="*0-9">

						<input type="hidden" value="isnull" name="isnull" /></td>

				</tr>
			</s:if>

			<s:if test="cost!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='cost.id.factNo'/>"
							name="cost.id.factNo" readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='cost.id.factCode'/>"
							name="cost.id.factCode" readonly /> </font></td>
				</tr>
				<tr>
					<td class="td_show_title">年月</td>

					<td class="td_input"><input type="text" style="color:blue"
						id="yymm" value="<s:date name='cost.id.yymm' format='yyyyMM'/>"
						name="yymm" readonly /></td>
					<td class="td_show_title">當月實際耗料(KG)</td>
					<td class="td_input"><input type="text" name="cost.actlost"
						value="<s:property value='cost.actlost' />" datatype="*0-9">

						<input type="hidden" value="notnull" name="isnull" id="onModulus" />
					</td>
				</tr>

			</s:if>


			<tr>
				<td class="td_show_title">當月生產膠底總毛重(KG)</td>
				<td class="td_input"><input type="text"
					name="cost.avgbuttomweight" datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.avgbuttomweight)}' />">
				</td>
				<td class="td_show_title">當月生產膠底總淨重(KG)</td>
				<td class="td_input"><input type="text"
					name="cost.avgbuttomweight2" datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.avgbuttomweight2)}' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">非膠底耗用重量(KG)</td>
				<td class="td_input"><input type="text" name="cost.noGlueWeight"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.noGlueWeight)}' />">
				</td>
				<td class="td_show_title">生產產值(USD)</td>
				<td class="td_input"><input type="text" name="cost.productedNum"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.productedNum)}' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">當月生產成本金額(USD)</td>
				<td class="td_input"><input type="text" name="cost.avgprice"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.avgprice)}' />">
				</td>
				<td class="td_show_title">總原料庫存量(KG)</td>
				<td class="td_input"><input type="text" name="cost.totalstore"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.totalstore)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">總原料庫存金額(USD)</td>
				<td class="td_input"><input type="text"
					name="cost.totalstoremoney" datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.totalstoremoney)}' />">
				</td>
				<td class="td_show_title">膠類庫存量(KG)</td>
				<td class="td_input"><input type="text" name="cost.gluestore"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.gluestore)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">膠類庫存金額(USD)</td>
				<td class="td_input"><input type="text"
					name="cost.gluestoremoney" datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.gluestoremoney)}' />">


				</td>
				<td class="td_show_title">色料用量(KG)</td>
				<td class="td_input"><input type="text" name="cost.colorused"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.colorused)}' />">
				</td>
			</tr>

			<tr>
				<td class="td_show_title">藥品用量(KG)</td>
				<td class="td_input"><input type="text" name="cost.drugsused"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.drugsused)}' />">
				</td>
				<td class="td_show_title">離型劑用量金額(USD)</td>
				<td class="td_input"><input type="text" name="cost.leavemoney"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.leavemoney)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">白色回收粉(KG)</td>
				<td class="td_input"><input type="text" name="cost.whitenum"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.whitenum)}' />">
				</td>
				<td class="td_show_title">黑色回收粉(KG)</td>
				<td class="td_input"><input type="text" name="cost.blacknum"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.blacknum)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">生膠回收粉(KG)</td>
				<td class="td_input"><input type="text" name="cost.gluenum"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.gluenum)}' />">
				</td>
				<td class="td_show_title">灰色回收粉(KG)</td>
				<td class="td_input"><input type="text" name="cost.greynum"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.greynum)}' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">其它回收粉(KG)</td>
				<td class="td_input"><input type="text" name="cost.othernum"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.othernum)}' />">

					<input type="hidden"
					value="<s:property value='#session.loginUser.username'/>"
					name="cost.username" /> <!-- 添加用戶名 --></td>
                <%-- <td class="td_show_title">其它銷售耗用總料重(KG)</td>
				<td class="td_input"><input type="text" name="cost.otherweight"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(cost.otherweight)}' />">
				</td> --%>
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
