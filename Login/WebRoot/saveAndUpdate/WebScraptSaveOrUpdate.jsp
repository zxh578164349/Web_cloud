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
<link rel="stylesheet" type="text/css" href="css/select_beautiful.css">
<script type="text/javascript">

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
					//location.href="/Login/webScrapt_getList";
					loadUrl("/Login/webScrapt_getList");
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
          webscraptjs.check(factno,factcode,yymmdd,function(x){
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
	 loadUrl("/Login/webScrapt_getList3?backIndex=1");
 }  
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webscraptjs.js'></script>
<!-- <script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script> -->
</head>
<body>
	<form action="webScrapt_addscrapt" method="post" id="form">
	<h2>廢品邊料(每月輸入)</h2>
		<table class="table table-condensed"
			id="msg1">
			  
			
			<tbody id="tb_list_info2">
				<s:if test="scrapt==null">
					<tr>
						<td class="td_show_title"><span id="sfactNo2"></span>廠別</td>
						<td class="td_input"><s:if test="#session.factNo!='tw'">
								<input type="text" style="color:blue" name="scrapt.id.factNo"
									value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span>
							</s:if> <s:if test="#session.factNo=='tw'">
								<select style="color:blue" name="scrapt.id.factNo"
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
							class="Wdate" onClick="WdatePicker();" id="dwr_yymmdd" onchange="check()" datatype="*"/><span id="error3"></span></td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><s:if test="#session.factNo=='tw'">
								<select name="scrapt.id.factCode" datatype="*" id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
								</select><span id="error2"></span>
							</s:if> <s:else>
								<select name="scrapt.id.factCode" datatype="*" id="dwrFactArea" onchange="check()">
									<option value="">請選擇廠別狀態</option>
									<s:iterator value="#session.factAreas_login" id="temp">
										<option value="${temp}">${temp}</option>
									</s:iterator>
								</select><span id="error2"></span>
							</s:else>
						</td>
						<td class="td_show_title">邊料重量</td>
						<td class="td_input"><input type="text"
							name="scrapt.sideweit"
							value="<s:property value='scrapt.sideweit' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>
				<s:if test="scrapt!=null">
					<input type="hidden" value="1" name="bs" />
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><font color="blue"><input
								type="text" id="factNo" style="color:blue"
								value="<s:property value='scrapt.id.factNo'/>"
								name="scrapt.id.factNo" readonly /> </font>
						</td>
						<td class="td_show_title">年月</td>
						<td class="td_input"><input type="text" style="color:blue"
							id="yymm" value="<s:date name='scrapt.id.yymm' format='yyyyMM'/>"
							name="yymm" readonly />
							<input type="hidden" value="${loginUser.username}" name="scrapt.usernameUd"/> <!--  修改者-->
						</td>
					</tr>
					<tr>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input">
						<input type="text" value="<s:property value='scrapt.id.factCode'/>" name="scrapt.id.factCode" style="color:blue" readonly/>
						<!-- <select name="scrapt.id.factCode"
							datatype="*">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_other" id="temp">
									<s:if test="#session.temp==scrapt.id.factCode">
										<option value="${temp}" selected>${temp}</option>
									</s:if>
									<s:else>
										<option value="${temp}">${temp}</option>
									</s:else>
								</s:iterator>
						</select>-->						
						</td>
						<td class="td_show_title">邊料重量(KG)</td>
						<td class="td_input"><input type="text"
							name="scrapt.sideweit"
							value="<s:property value='scrapt.sideweit' />" datatype="*0-6">
						</td>
					</tr>
				</s:if>

				<tr>
					<td class="td_show_title">不良雙數(雙)</td>
					<td class="td_input"><input type="text" name="scrapt.badcount"
						value="<s:property value='scrapt.badcount' />" datatype="*0-6">
					</td>
					<td class="td_show_title">不良重量(KG)</td>
					<td class="td_input"><input type="text" name="scrapt.badweit"
						value="<s:property value='scrapt.badweit' />" datatype="*0-6">
					</td>
					<td class="td_info"><div id="questionTip" style="width:100%;"></div>
					</td>
				</tr>
				<tr>
					<td class="td_show_title">其他報廢重量(KG)</td>
					<td class="td_input"><input type="text"
						name="scrapt.otherbadweight"
						value="<s:property value='scrapt.otherbadweight' />"
						datatype="*0-6" />
						<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="scrapt.username" />
					</td>					
				</tr>
			</tbody>
		</table>
		<center>
			<input type="button" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="button" value="返回" id="btn_back"
				onclick="javascript:back()" class="btn btn-primary"/>
		</center>
	</form>
</body>
</html>
