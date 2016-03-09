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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
</head>
<script type="text/javascript">
	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			showAllError : true,
			tipSweep:true,
			datatype : {
				"9_1" : /^-?\d{1,9}(\.[0-9]{1})?$/,
				"9_2" : /^-?\d{1,9}(\.[0-9]{1,2})?$/,
				"9_4" : /^-?\d{1,9}(\.[0-9]{1,4})?$/

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
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function back() {
		loadUrl("/Login/kpifact_findPageBean3?backIndex=1");
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          kpifactjs.findById(factno,factcode,yymmdd,function(x){
              if(x!=null){
              alert("數據庫已存在("+factno+factcode+yymmdd+"),請重新選擇年份");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="已鎖定";
              document.getElementById("sub").style.color="red";
              document.getElementById("error3").innerHTML="<font color='color'>！</font>";
          }else{
            document.getElementById("sub").disabled=false;
            document.getElementById("sub").value="確定";
            document.getElementById("sub").style.color="white";
            document.getElementById("error3").innerHTML="";
          }        
          });               
       }                    
   }
             /*禁止空格輸入*/
/* window.onload=function(){            
            var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            }  
        }  */
 jq(function(){
    var inputs=document.getElementsByTagName("input"); 
            for (var i=0;i<inputs.length; i++) {  
                if(inputs[i].getAttribute("type")=="text") 
                 inputs[i].onkeyup=function(){ 
                    this.value=this.value.replace(/(^\s+)|\s+$/g,""); 
                 }; 
            } 
 });       
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webcostjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kpifactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="kpifact_add" method="post" id="form">
	 <h2>KPI年度目標(複製)</h2>
		<table class="table table-condensed">		
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text"  style="color:blue"
							value="<s:property value='kpi.id.factNo'/>"
							name="kpi.id.factNo" readonly id="dwr_factno"/> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text"  style="color:blue"
							value="<s:property value='kpi.id.factCode'/>"
							name="kpi.id.factCode" readonly id="dwrFactArea"/> </font></td>
				</tr>
				<tr>
					<td class="td_show_title">年份</td>
					<td class="td_input"><input type="text" style="color:blue"
						id="dwr_yymmdd" value=""
						name="kpi.id.yyyy" onclick="WdatePicker({dateFmt:'yyyyMM'})" datatype="*" class="Wdate" onchange="check()"/><span id="error3"></span></td>
					<td class="td_show_title">當月產量(模)</td>
					<td class="td_input"><input type="text" name="kpi.thisYield"
						value="<s:property value='kpi.thisYield' />" datatype="9_1">
					</td>
				</tr>

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
					datatype="9_4"
					value="<s:property value='kpi.waterTon' />">
				</td>
				<td class="td_show_title">电度数单耗(度/模)</td>
				<td class="td_input"><input type="text" name="kpi.lightDu"
					datatype="9_4"
					value="<s:property value='kpi.lightDu' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">蒸汽单耗(噸/模)</td>
				<td class="td_input"><input type="text" name="kpi.gasUsd"
					datatype="9_4"
					value="<s:property value='kpi.gasUsd' />">
				</td>
				<td class="td_show_title">修繕單耗(USD/模)</td>
				<td class="td_input"><input type="text" name="kpi.wasteUsd"
					datatype="9_4"
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
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;
			
				<input type="button" value="返回" id="btn_back"
					onclick="back()" class="btn btn-primary"/>
			

		</center>
	</form>

</body>
</html>
