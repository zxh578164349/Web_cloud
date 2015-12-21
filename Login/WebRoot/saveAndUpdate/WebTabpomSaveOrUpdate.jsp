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
			tipSweep:true,
			showAllError : true,
			datatype : {
				"*0-6" : /^-?\d{1,12}(\.[0-9]{1,3})?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="/Login/webwlo_getList";
				}if(data=="1"){
					alert(data.responseText);
				}
				if(data=="2"){
					layer.msg("數據已經存在",3,1);
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
          webwlojs.check(factno,factcode,yymmdd,function(x){
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
	location.href="/Login/webwlo_getList";
}             
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webwlojs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>
</head>

<body>
	<form action="webtabpom_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="msg1">
			  <caption>實驗室形體物性</caption>
			<tbody id="tb_list_info">
				<tr></tr>
			</tbody>
			<tbody id="tb_list_info2">
				
					<tr>
						<td class="td_show_title">物性編號</td>
						<s:if test="tabpom==null">
						<td class="td_input">
						   <input type="text" name="tabpom.pomNo"/>
						</td>
						</s:if>
						<s:else>
						   <input type="text" name="tabpom.pomNo" style="color:blue" readonly/>
						</s:else>
						<td class="td_show_title">物料名稱</td>
						<td class="td_input"><input type="text" name="tabpom.pomName" value="<s:property value='tabpom.pomName'/>"/></td><s:property value=''/>
					</tr>
					<tr>
						<td class="td_show_title">指定料</td>
						<td class="td_input">
						  是<input type="radio" name="tabpom.spematerial" value="0"/>&nbsp;
						  否<input type="radio" name="tabpom.spematerial" value="1"/>
						</td>
						<td class="td_show_title">部件</td>
						<td class="td_input">
						     <select name="tabpom.component">
						        <option value="RB">RB</option>
						        <option value="MD">MD</option>
						     </select>
						</td>
					</tr>				
				<tr>
					<td class="td_show_title">生產工廠</td>
					<td class="td_input">
					    <input type="checkbox" name="list_fact" value="631">加元一廠&nbsp;
					    <input type="checkbox" name="list_fact" value="687">加元三廠
					</td>
					<%--<td class="td_show_title">用電量(度)</td>
					<td class="td_input"><input type="text" name="tabpom.electricdu"
						value="<s:property value='tabpom.electricdu' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				--%></tr>
				
				
				<%--<tr>
					<td class="td_show_title">用電金額(USD)</td>
					<td class="td_input"><input type="text" name="tabpom.electricusd"
						value="<s:property value='tabpom.electricusd' />" datatype="*0-6">
					</td>
					<td class="td_show_title">用蒸汽量(噸)</td>
					<td class="td_input"><input type="text" name="tabpom.gaston"
						value="<s:property value='tabpom.gaston' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">用蒸汽金額(USD)</td>
					<td class="td_input"><input type="text" name="tabpom.gasusd"
						value="<s:property value='tabpom.gasusd' />" datatype="*0-6">
					</td>
					<td class="td_show_title">用柴油量(噸)</td>
					<td class="td_input"><input type="text" name="tabpom.oilton"
						value="<s:property value='tabpom.oilton' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">用柴油金額(USD)</td>
					<td class="td_input"><input type="text" name="tabpom.oilusd"
						value="<s:property value='tabpom.oilusd' />" datatype="*0-6" />						
					</td>
					<td class="td_show_title">修繕金額(USD)</td>
					<td class="td_input"><input type="text" name="tabpom.repiarMoney"
						value="<s:property value='tabpom.repiarMoney' />" datatype="*0-6" />
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="tabpom.username" />
					</td>
					
				</tr>
			--%></tbody>
		</table>
		<center>
			<input type="button" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;			 
				<input type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; 			
				<input type="button" value="返回" id="btn_back"
				onclick="javascript:back()" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
		</center>
	</form>
</body>
</html>
