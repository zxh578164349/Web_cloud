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

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_prediction.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>

<body>
	<form action="kpifact_add" method="post" id="form">
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
				<td class="td_show_title">機臺利用率(%)</td>
				<td class="td_input"><input type="text" name="kpi.mutiRate"
					datatype="9_4"
					value="<s:property value='kpi.mutiRate' />">
				</td>
				<td class="td_show_title">產能達成率(%)</td>
				<td class="td_input"><input type="text" name="kpi.productRate"
					datatype="9_4"
					value="<s:property value='kpi.productRate' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">直工人均产能(模/人)</td>
				<td class="td_input"><input type="text" name="kpi.avgZgpro"
					datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.avgZgpro)}' />">
				</td>
				<td class="td_show_title">全厂人均产能(模/人)</td>
				<td class="td_input"><input type="text" name="kpi.avgPerpro"
					datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.avgPerpro)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">全廠人均時產能(模/H)</td>
				<td class="td_input"><input type="text"
					name="kpi.avgFactpro" datatype="9_4"
					value="<s:property value='kpi.avgFactpro' />">
				</td>
				<td class="td_show_title">成倉庫存(雙)</td>
				<td class="td_input"><input type="text" name="kpi.storeNum"
					datatype="9_4"
					value="<s:property value='kpi.storeNum' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">已出未請(雙)</td>
				<td class="td_input"><input type="text"
					name="kpi.outRequest" datatype="n1-9"
					value="<s:property value='%{formatInteger(kpi.outRequest)}' />">


				</td>
				<td class="td_show_title">生產與請款差異率(%)</td>
				<td class="td_input"><input type="text" name="kpi.outrequestRate"
					datatype="9_4"
					value="<s:property value='%{formatInteger(kpi.outrequestRate)}' />">
				</td>
			</tr>

			<tr>
				<td class="td_show_title">銷貨收入(USD)</td>
				<td class="td_input"><input type="text" name="kpi.slIncome"
					datatype="9_4"
					value="<s:property value='kpi.slIncome' />">
				</td>
				<td class="td_show_title">主材料成本比率(%)</td>
				<td class="td_input"><input type="text" name="kpi.mainRate"
					datatype="9_4"
					value="<s:property value='kpi.mainRate' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">人工成本率(%)</td>
				<td class="td_input"><input type="text" name="kpi.pcostRate"
					datatype="my9_4"
					value="<s:property value='kpi.pcostRate' />">
				</td>
				<td class="td_show_title">費用成本率(%)</td>
				<td class="td_input"><input type="text" name="kpi.ccostRate"
					datatype="my9_4"
					value="<s:property value='kpi.ccostRate' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">修繕單耗(USD/模)</td>
				<td class="td_input"><input type="text" name="kpi.wasteUsd"
					datatype="my9_4"
					value="<s:property value='kpi.wasteUsd' />">
				</td>
				<td class="td_show_title">平均單價(USD/雙)</td>
				<td class="td_input"><input type="text" name="kpi.perPrice"
					datatype="my9_4"
					value="<s:property value='kpi.perPrice' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">全廠人均薪資(USD/人)</td>
				<td class="td_input"><input type="text" name="kpi.perSalar"
					datatype="9_4"
					value="<s:property value='kpi.perSalar' />">
				</td> 
				<td class="td_show_title">人均產值(USD/人)</td>
				<td class="td_input"><input type="text" name="kpi.avgPermoney"
					datatype="9_4"
					value="<s:property value='kpi.avgPermoney' />">
				</td>	              
			</tr>
			<tr>
				<td class="td_show_title">人薪產值(USD/人)</td>
				<td class="td_input"><input type="text" name="kpi.permoney"
					datatype="9_4"
					value="<s:property value='kpi.permoney' />">
				</td>
				<td class="td_show_title">全廠總損耗(%)</td>
				<td class="td_input"><input type="text" name="kpi.wasteFact"
					datatype="9_4"
					value="<s:property value='kpi.wasteFact' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">無形損耗(%)</td>
				<td class="td_input"><input type="text" name="kpi.wasteNo"
					datatype="9_4"
					value="<s:property value='kpi.wasteNo' />">
				</td>
				<td class="td_show_title">邊料率(%)</td>
				<td class="td_input"><input type="text" name="kpi.sideRate"
					datatype="9_4"
					value="<s:property value='kpi.sideRate' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">不良率(%)</td>
				<td class="td_input"><input type="text" name="kpi.uhealRate"
					datatype="9_4"
					value="<s:property value='kpi.uhealRate' />">
				</td>
				<td class="td_show_title">報廢率(%)<br>廠補率(%)</td>
				<td class="td_input"><span><input type="text" name="kpi.wasteRate"
					datatype="9_4"
					value="<s:property value='kpi.wasteRate' />"></span><br>
					<span>
					<input type="text" name="kpi.factaddRate"
					datatype="9_4"
					value="<s:property value='kpi.factaddRate' />">
					</span>					
				</td>
				
			</tr>
			
			
			
			<tr>
				<td class="td_show_title">水用量单耗(噸/模)</td>
				<td class="td_input"><input type="text" name="kpi.waterTon"
					datatype="9_4"
					value="<s:property value='%{formatDouble_5(kpi.waterTon)}'/>">
				</td>
				<td class="td_show_title">用水金額單耗(USD/模)</td>
				<td class="td_input"><input type="text" name="kpi.waterUsd"
					datatype="9_4"
					value="<s:property value='%{formatDouble_5(kpi.waterUsd)}' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">电度数单耗(度/模)</td>
				<td class="td_input"><input type="text" name="kpi.lightDu"
					datatype="9_4"
					value="<s:property value='%{formatDouble_5(kpi.lightDu)}' />">
				</td>
				<td class="td_show_title">用電金額單耗(USD/模)</td>
				<td class="td_input"><input type="text" name="kpi.lightUsd"
					datatype="9_4"
					value="<s:property value='%{formatDouble_5(kpi.lightUsd)}' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">蒸汽用量單耗(噸/模)</td>
				<td class="td_input"><input type="text" name="kpi.gasTon"
					datatype="9_4"
					value="<s:property value='%{formatDouble_5(kpi.gasTon)}' />">
				</td>
				<td class="td_show_title">用汽金額單耗(USD/模)</td>
				<td class="td_input"><input type="text" name="kpi.gasUsd"
					datatype="9_4"
					value="<s:property value='%{formatDouble_5(kpi.gasUsd)}' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">回頭料(%)</td>
				<td class="td_input"><input type="text" name="kpi.bheadRate"
					datatype="9_4"
					value="<s:property value='kpi.bheadRate' />">
				</td>
				<td class="td_show_title">油壓退料(%)</td>
				<td class="td_input"><input type="text" name="kpi.bpreRate"
					datatype="9_4"
					value="<s:property value='kpi.bpreRate' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">回流率(%)</td>
				<td class="td_input"><input type="text" name="kpi.bflowRate"
					datatype="9_4"
					value="<s:property value='kpi.bflowRate' />">
				</td>
				<td class="td_show_title">藥品用量單耗(g/模)</td>
				<td class="td_input"><input type="text" name="kpi.drugWast"
					datatype="9_4"
					value="<s:property value='kpi.drugWast' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">色料用量單耗(g/模)</td>
				<td class="td_input"><input type="text" name="kpi.clrWast"
					datatype="9_4"
					value="<s:property value='kpi.clrWast' />">
				</td>
				<td class="td_show_title">離型劑金額單耗(USD/模)</td>
				<td class="td_input"><input type="text" name="kpi.leaveUsd"
					datatype="9_4"
					value="<s:property value='kpi.leaveUsd' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">直間比</td>
				<td class="td_input"><input type="text" name="kpi.zjRate"
					datatype="9_4"
					value="<s:property value='kpi.zjRate' />">
				</td>
				<td class="td_show_title">直工離職率(%)</td>
				<td class="td_input"><input type="text" name="kpi.zgleaveRate"
					datatype="9_4"
					value="<s:property value='kpi.zgleaveRate' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">全廠離職率(%)</td>
				<td class="td_input"><input type="text" name="kpi.factleaveRate"
					datatype="9_4"
					value="<s:property value='kpi.factleaveRate' />">
				</td>
				<td class="td_show_title">工傷件數(件)</td>
				<td class="td_input"><input type="text" name="kpi.hurtNum"
					datatype="9_4"
					value="<s:property value='kpi.hurtNum' />">
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
				"9_4" : /^-?\d{1,9}(\.[0-9]{1,4})?$/,
				"8_5" :	/^-?\d{1,8}(\.[0-9]{1,5})?$/	

			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="kpifact_findPageBean";
					loadUrl("kpifact_findPageBean");
				}
				if(data=="1"){
					//alert(data.responseText);
					layer.msg("提交失敗!",3,3);
				}
			}
		});
		demo.tipmsg.w["9_1"] = "只能數字且不超過9位數,可保留一位以內小數";
		demo.tipmsg.w["9_2"] = "只能數字且不超過9位數,可保留兩位小數";
		demo.tipmsg.w["9_4"] = "只能數字且不超過9位數,可保留四位小數";
		demo.tipmsg.w["8_5"] = "只能數字且不超8位數,可保留5位小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function back() {
		loadUrl("kpifact_findPageBean3?backIndex=1");
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

 jq(function(){
	 goTrim();
 });       
</script>
<script type='text/javascript' src='dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='dwr/interface/kpifactjs.js'></script>
</body>
</html>
