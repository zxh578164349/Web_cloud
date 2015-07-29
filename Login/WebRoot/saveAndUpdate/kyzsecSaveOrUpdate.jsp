<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Yield_data.jsp' starting page</title>

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
<script type="text/javascript" src="jquery/DatePicker/my_WdatePicker.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		var j = jQuery.noConflict();
		var demo = j("#form").Validform({
			btnSubmit : "#sub",
			tiptype : 3,
			showAllError : true,
			ignoreHidden : true,
			tipSweep : true,
			callback : function(form) {
				document.getElementById("mydiv").style.display = "block";
				form[0].submit();
				return false;
			},
			datatype : {
				"my0-6" : /^\d{0,9}(\.[0-9]{1,3})?$/,
				"my1-6" : /^[1-9]{1}\d{0,8}(\.[0-9]{1,3})?$/,
				"my0-7" : /^\d{0,7}(\.[0-9]{1})?$/

			}
		});
		demo.tipmsg.w["my0-6"] = "只能數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my1-6"] = "不為0的數字且不超過9位數,可保留三位以內小數";
		demo.tipmsg.w["my0-7"] = "只能數字且不超過7位數,可保留一位以內小數";

	});

	function getFactArea(mid) {
		document.getElementById("dwrFactArea").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea", x);
		});
	}

	function getFactArea2(mid) {
		document.getElementById("dwrFactArea2").length = 1;
		webfactjs.findFactCodeByFactNo(mid, function(x) {
			//alert(mid);
			dwr.util.addOptions("dwrFactArea2", x);
		});
	}

	function back() {
		if (navigator.userAgent.indexOf("MSIE") > 0) {
			document.getElementById("mydiv").style.display = "block";
			window.location.href = "../kyzsec_findPageBean";
		} else {
			document.getElementById("mydiv").style.display = "block";
			window.location.href = "kyzsec_findPageBean";
		}

	}
	function makeSecno(obj){
	   var factno=document.getElementById("dwrFactNo").value;
	   if(factno!=""){
	      kyzsecjs.makeSecNo(obj,function(x){
	         dwr.util.setValue("secno",x);
	      });
	   }
	}	
$(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});
</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzsecjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<body>

	<form action="kyzsec_add" method="post" id="form">

		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="table1">

			<s:if test="kyzsec==null">

				<s:if test="#session.factNo!='tw'">
					<tr>
						<td class="td_show_title" >廠別</td>
						<td class="td_input">
						   <select name="kyzsec.id.factNo" style="color:blue" id="dwrFactNo" onchange="makeSecno(this.value)" datatype="*">
						      <option value="">請選擇廠別</option>
						      <option value="${factNo}">${factNo}</option>
						   </select>						   
						</td>
						<td class="td_show_title">組別代號</td>
						<td class="td_input"><input type="text" name="kyzsec.id.secNo" value="" readonly style="color:blue" id="secno"/></td>
						<%-- <td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode"
							datatype="*" id="factarea">
								<option value="">請選擇廠別狀態</option>
								<s:iterator value="#session.factAreas_login" id="temp">
									<option value="${temp}">${temp}</option>
								</s:iterator>
						</select></td> --%>
						
					</tr>
				</s:if>
				<s:if test="#session.factNo=='tw'">
					<tr>
						<td class="td_show_title">廠別</td>
						<td class="td_input"><select style="color:blue"
							name="kyzsec.id.factNo" datatype="*" id="dwrFactNo"
							onchange="makeSecno(this.value)">
								<option value="">請選擇廠別</option>
								<s:iterator value="#session.facts" id="temp">
									<option value="${temp[0]}">${temp[1]
										}&nbsp;(${temp[0]})</option>
								</s:iterator>
						</select></td>
						<td class="td_show_title">組別代號</td>
						<td class="td_input"><input type="text" name="kyzsec.id.secNo" value="" readonly style="color:blue" id="secno"/></td>
						<!-- <td class="td_show_title">廠別狀態</td>
						<td class="td_input"><select name="ydata.id.factCode"
							datatype="*" id="dwrFactArea">
								<option value="">請選擇廠別狀態</option>
						</select></td> -->
						
					</tr>
				</s:if>

				
			</s:if>

			<s:if test="kyzsec!=null">

				<tr>
					<td class="td_show_title">廠別</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="factNo" style="color:blue"
							value="<s:property value='kyzsec.id.factNo'/>"
							name="kyzsec.id.factNo" readonly /> </font></td>
					<td class="td_show_title">組別代號</td>
					<td class="td_input"><font color="blue"><input
							type="text" id="secNo" style="color:blue"
							value="<s:property value='kyzsec.id.secNo'/>"
							name="kyzsec.id.secNo" readonly /> </font></td>
				</tr>				
			</s:if>
			<tr>


					<td class="td_show_title">組別名稱<br></td>
					<td class="td_input"><input type="text" name="kyzsec.secNm" datatype="*0-40" value="<s:property value='kyzsec.secNm'/>"/></td>						  					
					<td class="td_show_title">區域</td>
					<td class="td_input"><input type="text" name="kyzsec.areaMk"  value="<s:property value='kyzsec.areaMk'/>" datatype="*0-10"/>	</td>					 
						
				</tr>
				<tr>
				  <td class="td_show_title">所屬部門</td>
				  <td class="td_input"><input type="text" name="kyzsec.deptNo" value="<s:property value='kyzsec.deptNo'/>" datatype="*0-20"/></td>
				  <td class="td_show_title">是否使用</td>
				  <td class="td_input">
				    <select name="kyzsec.useMk">
				      <option value="">請選擇</option>
				      <s:if test="kyzsec.useMk==0">
				        <option value="0" selected>是</option>
				      </s:if>
				      <s:else>
				         <option value="0">是</option>
				      </s:else>
				      <s:if test="kyzsec.useMk==1">
				         <option value="1" selected>否</option>
				      </s:if>
				      <s:else>
				         <option value="1">否</option>
				      </s:else>				     
				    </select>
				  </td>
				</tr>
		</table>





		<center>
			<input type="button" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;
			<!-- <s:if test="kyzsec!=null">
				<input type="button" value="返回" onclick="javascript:location.href='kyzsec_findPageBean'" id="btn_back"
				onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
					
			</s:if>
		　　<s:else>
		        <input type="button" value="返回" onclick="back()" id="btn_back"
				onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
		    </s:else> -->
		    
		    			
		</center>
	</form>

	<div id="mydiv">
		<p>
			<img alt="" src="images/loading004.gif"><br> Loading....
		</p>
	</div>

</body>

</html>
