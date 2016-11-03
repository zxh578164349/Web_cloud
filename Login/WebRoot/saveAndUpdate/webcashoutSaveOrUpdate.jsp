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
<link rel="stylesheet" type="text/css" href="css/form.css" />
</head>


<body>
	<form action="webcashout_add" method="post" id="form">	
		<table class="table table-condensed">
		　　
			<s:if test="cashout==null">
				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><input type="text" style="color:blue"
							name="cashout.id.factNo" value="${factNo}" readonly id="dwr_factno"/><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="cashout.id.factCode"
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
							name="cashout.id.factNo" datatype="*"
							onchange="getFactArea(this.value),check()" id="dwr_factno">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select><span id="error1"></span></td>
						<td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="cashout.id.factCode"
							datatype="*" id="dwrFactArea" onchange="check()">
								<option value="">請選擇廠別狀態</option>

						</select><span id="error2"></span></td>
					</tr>
				</s:if>


				<tr>
					<td class="td_show_title">日期</td>
					<td class="td_input"><input type="text" name="date"
						onClick="WdatePicker({dateFmt:'yyyyMMdd'})" class="Wdate" datatype="*" id="dwr_yymmdd" onchange="check()"/><span id="error3"></span></td>
					<td class="td_show_title">生管實際交單雙數(雙)</td>
					<td class="td_input"><input type="text" name="cashout.realpairs"
						value="<s:property value='cashout.realpairs' />" datatype="*0-9">

						<input type="hidden" value="isnull" name="isnull" /></td>

				</tr>
			</s:if>

			<s:if test="cashout!=null">
				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='cashout.id.factNo'/>"
							name="cashout.id.factNo" readonly /> </font></td>
					<td class="td_show_title">廠別狀態</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="billNo" style="color:blue"
							value="<s:property value='cashout.id.factCode'/>"
							name="cashout.id.factCode" readonly /> </font>
							<input type="hidden" value="${loginUser.username}" name="cashout.usernameUd"/> <!--  修改者-->
							</td>
				</tr>
				<tr>
					<td class="td_show_title">日期</td>

					<td class="td_input"><input type="text" style="color:blue"
						id="yymm" value="<s:date name='cashout.id.yymmdd' format='yyyyMMdd'/>"
						name="date" readonly /></td>
					<td class="td_show_title">生管實際交單雙數(雙)</td>
					<td class="td_input"><input type="text" name="cashout.realpairs"
						value="<s:property value='cashout.realpairs' />" datatype="*0-9">

						<input type="hidden" value="notnull" name="isnull"/>
					</td>
				</tr>

			</s:if>


			<tr>
				<td class="td_show_title">生管交單折算金額(USD)</td>
				<td class="td_input"><input type="text"
					name="cashout.convertmoney" datatype="*0-9"
					value="<s:property value='cashout.convertmoney' />">
				</td>
				<td class="td_show_title">企劃實際請款雙數(雙)</td>
				<td class="td_input"><input type="text"
					name="cashout.realcashoutpairs" datatype="*0-9"
					value="<s:property value='cashout.realcashoutpairs' />">
				</td>
			</tr>
			<tr>
				<td class="td_show_title">企劃實際請款金額(USD)</td>
				<td class="td_input"><input type="text" name="cashout.realcashoutmoney"
					datatype="*0-9"
					value="<s:property value='cashout.realcashoutmoney' />">
					
					<input type="hidden" value="<s:property value='#session.loginUser.username'/>" name="cashout.username" /><!-- 用戶名 -->
				</td>				
			</tr>								
		</table>
		<center>
			<input type="submit" id="sub" value="確定" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp; <input
				type="reset" id="reset" value="重置" class="btn btn-primary"/>&nbsp;&nbsp;&nbsp;			
				<input type="button" value="返回" onclick="back()" id="btn_back" class="btn btn-primary"/>

		</center>
	</form>


<script type="text/javascript">

	jq(function() {
		var demo = jq("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 4,
			tipSweep : true,
			ignoreHidden : true,
			showAllError : true,
			datatype : {
				"*0-9" : /^-?\d{1,9}(\.[0-9]{1,3})?$/,
				"*0-7" : /^-?\d{1,7}(\.[0-9])?$/
			},
			ajaxPost:true,
			callback:function(data){
				if(data=="0"){
					layer.msg("提交成功!",3,1);
					//location.href="/Login/webcashout_findPageBean";
					loadUrl("/Login/webcashout_findPageBean");
				}else{
					//alert(data.responseText);
					layer.msg("提交失敗",3,3);
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
			loadUrl("/Login/webcashout_findPageBean3?backIndex=1");
	}
	 function check(){
       var factno=document.getElementById("dwr_factno").value;
       var factcode=document.getElementById("dwrFactArea").value;
       var yymmdd=document.getElementById("dwr_yymmdd").value;
       if(factno!=""&&factcode!=""&&yymmdd!=""){
          webcashoutjs.findById(factno,factcode,yymmdd,function(x){
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
jq(function(){
	goTrim();
});
             
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/webcashoutjs.js'></script>
</body>
</html>
