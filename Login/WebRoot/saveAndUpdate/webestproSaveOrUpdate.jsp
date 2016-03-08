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
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-9" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
				"*0-7" : /^-?\d{1,7}(\.[0-9])?$/,
				"*1-9" : /^-?[1-9]{1}\d{1,8}(\.[0-9]{1,3})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					loadUrl("/Login/webestpro_findPageBean");
					//location.href="/Login/webestpro_findPageBean";
				}else{
					//alert(data.responseText);
					layer.msg("提交失敗",3,3);
				}				
			}
		});
		demo.tipmsg.w["*0-9"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["*0-7"] = "只能數字且不超過7位數,可保留一位小數";
		demo.tipmsg.w["*1-9"] = "十位以上數字且不超過9位數,可保留三位小數";
	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo_show_dw(mid, function(x) {
			dwr.util.addOptions("dwrFactArea", x);
		});

	}
	function back() {
		    loadUrl("/Login/webestpro_findPageBean3?backIndex=1");	
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       var protype=document.getElementById("dwr_type").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""&&protype!=""){
          webestprojs.check(factno,factcode,yymmdd,protype,function(x){
              if(x==true){
              alert("數據庫已存在("+factno+factcode+yymmdd+")");
              document.getElementById("sub").disabled=true;
              document.getElementById("sub").value="已鎖定";
              document.getElementById("sub").style.color="red";
              document.getElementById("error1").innerHTML="<font color='color'>！</font>";
              document.getElementById("error2").innerHTML="<font color='color'>！</font>";
              document.getElementById("error3").innerHTML="<font color='color'>！</font>";
              document.getElementById("error4").innerHTML="<font color='color'>！</font>";
          }else{
            document.getElementById("sub").disabled=false;
            document.getElementById("sub").value="確定";
            document.getElementById("sub").style.color="white";
            document.getElementById("error1").innerHTML="";
            document.getElementById("error2").innerHTML="";
            document.getElementById("error3").innerHTML="";
            document.getElementById("error4").innerHTML="";
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
<script type='text/javascript' src='/Login/dwr/interface/webestprojs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>


<body>
	<form action="webestpro_add" method="post" id="form">
	<h2>預計生產</h2>
		<table class="table table-condensed">
		<tbody id="tb_list_info2">
			<s:if test="pro==null">				
					<tr>
					 <s:if test="#session.factNo!='tw'">
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="pro.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="pro.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select><span id="error2"></span></td>
						</s:if>
						<s:if test="#session.factNo=='tw'">
						<td class="td_show_title">廠別</td>
						<td class="td_input"><select style="color:blue"
							name="pro.id.factNo" datatype="*"
							onchange="getFactArea(this.value),check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="pro.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>
						</select><span id="error2"></span></td>
						</s:if> 
					</tr>				        
				<tr>
					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" name="yymm"
						onClick="WdatePicker()" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span></td>
					
                    <td class="td_show_title">類型</td>
			 　　　　 <td class="td_input">			     
			        <select name="pro.id.type" datatype="*" id="dwr_type" onchange="check()">
			        　　　　<option value="">請選擇</option>
			           <option value="zd">暫訂版</option>
			           <option value="tz">調整版</option>
			        </select> <span id="error4"></span>                                                 
						 <input type="hidden" value="isnull" name="isnull" />
			  </td>
			</tr>
			</s:if>
			<s:if test="pro!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='pro.id.factNo'/>" name="pro.id.factNo"
							readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='pro.id.factCode'/>"
							name="pro.id.factCode" readonly /> </font>
							<input type="hidden" value="${loginUser.username}" name="pro.usernameUd"/> <!--  修改者-->
							</td>
				</tr>
				<tr>
					<td class="td_show_title">年月</td>
					<td class="td_input"><input type="text" style="color:blue"
						id="yymm" value="<s:date name='pro.id.yymm' format='yyyyMM'/>"
						name="yymm" readonly /></td>
					<td class="td_show_title">類型</td>
					<td class="td_input">
					<input type="text" value="<s:property value='pro.id.type'/>" name="pro.id.type" readonly style="color:blue"/>
					 
						<input type="hidden" value="notnull" name="isnull" /></td>
				</tr>
			</s:if>

			<tr>
				<td class="td_show_title">機台戰力(模/月)</td>
				<td class="td_input"><input type="text" name="pro.machinepower"
					datatype="*1-9"
					value="<s:property value='%{formatDouble2(pro.machinepower)}' />">
				</td>
				<td class="td_show_title">預計生產天數(天)</td>
				<td class="td_input"><input type="text" name="pro.estdays"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(pro.estdays)}' />">
				</td>


			</tr>
			<tr>
				<td class="td_show_title">預計每日上模數(模)</td>
				<td class="td_input"><input type="text"
					name="pro.esteverymodel" datatype="*0-9"
					value="<s:property value='%{formatDouble2(pro.esteverymodel)}' />">
				</td>
				<td class="td_show_title">預計每日人數(人)</td>
				<td class="td_input"><input type="text"
					name="pro.esteverypeople" datatype="*0-9"
					value="<s:property value='%{formatDouble2(pro.esteverypeople)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">預計生產模數(模)</td>
				<td class="td_input"><input type="text" name="pro.estmodel"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(pro.estmodel)}' />">
				</td>
				<td class="td_show_title">預計生產雙數(雙)</td>
				<td class="td_input"><input type="text" name="pro.estnum"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(pro.estnum)}' />">
				</td>

			</tr>
			<tr>
				<td class="td_show_title">預計請款雙數(雙)</td>
				<td class="td_input"><input type="text" name="pro.estpay"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(pro.estpay)}' />">


				</td>
				<td class="td_show_title">預計請款金額(USD)</td>
				<td class="td_input"><input type="text" name="pro.estmoney"
					datatype="*0-9"
					value="<s:property value='%{formatDouble2(pro.estmoney)}' />">

					<input type="hidden"
					value="<s:property value='#session.loginUser.username'/>"
					name="pro.username" /> <!-- 添加用戶名 --></td>
			</tr>
			<tr>
			  <td class="td_show_title">有效孔位數(孔)</td>
					<td class="td_input"><input type="text" name="pro.hole"
						value="<s:property value='pro.hole' />" datatype="*0-9">
					 </td>
			　<td class="td_show_title">總機孔(孔)</td>
					<td class="td_input"><input type="text" name="pro.totalhole"
						value="<s:property value='pro.totalhole' />" datatype="*0-9">
					 </td>
			</tr>
			<tr>
			  <td class="td_show_title">工程樣品</td>
					<td class="td_input"><input type="text" name="pro.sample"
						value="<s:property value='pro.sample' />" datatype="*0-9">
					 </td>
			　<td class="td_show_title">輔料</td>
					<td class="td_input"><input type="text" name="pro.accessories"
						value="<s:property value='pro.accessories' />" datatype="*0-9">
					 </td>
			</tr>
			<tr>
			  <td class="td_show_title">其它</td>
					<td class="td_input"><input type="text" name="pro.other"
						value="<s:property value='pro.other' />" datatype="*0-9">
					 </td>			
			</tr>
         </tbody>
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;				
			<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>

		</center>
	</form>

</body>
</html>
