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
<style>
 Alert{
   font-size:22px;
 }
</style>
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
			window.location.href = "../kyzacct_findPageBean";
		} else {
			document.getElementById("mydiv").style.display = "block";
			window.location.href = "kyzacct_findPageBean";
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
	
	function checkAcctNo(obj){
	   kyzacctjs.checkAcctNo(obj,function(x){
	          if(x==false){
	             alert("科目代號  "+obj+" 已存在,請重新輸入!");	             	            
	           } 
	   });
	}
	
$(document).keyup(function(event){
   if(event.keyCode==13){
      submis();
   }
});
	
	

</script>
<script type='text/javascript' src='/Login/dwr/interface/webfactjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzsecjs.js'></script>
<script type='text/javascript' src='/Login/dwr/interface/kyzacctjs.js'></script>
<script type='text/javascript' src='/Login/dwr/engine.js'></script>
<script type='text/javascript' src='/Login/dwr/util.js'></script>

<body>

	<form action="kyzacct_add" method="post" id="form">
		<table width="100%" align="center" cellspacing="0" cellpadding="0"
			id="table1">							
					<tr>					  
						<td class="td_show_title" >科目代號</td>
					<s:if test="kyzacct==null">
						<td class="td_input">
						   <input type="text" name="kyzacct.acctNo" datatype="s1-8" onblur="checkAcctNo(this.value)"  />
						   <input type="hidden" name="isnull" value="isNull"/>						   
						</td>
					</s:if>
					<s:else>
					    <td class="td_input">
					       <input type="text" name="kyzacct.acctNo" value="<s:property value='kyzacct.acctNo'/>" style="color:blue" readonly/>
					       <input type="hidden" name="isnull" value="isNotnull">
					    </td>					    
					</s:else>
						<td class="td_show_title">科目名稱</td>
						<td class="td_input"><input type="text" name="kyzacct.acctName" value="<s:property value='kyzacct.acctName'/>" datatype="*0-50"/></td>											
					</tr>														
			　　<tr>
					<td class="td_show_title">分類代號<br></td>
					<td class="td_input"><input type="text" name="kyzacct.bacctNo" datatype="s0-10" value="<s:property value='kyzacct.bacctNo'/>"/></td>						  					
					<td class="td_show_title">分類名稱</td>
					<td class="td_input"><input type="text" name="kyzacct.bacctName"  value="<s:property value='kyzacct.bacctName'/>" datatype="*0-40"/>	</td>					 						
				</tr>				
		</table>


		<center>
			<input type="submit" id="sub" value="確定" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp; 
			<input type="reset" id="reset" value="重置" onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>&nbsp;&nbsp;&nbsp;
			<!-- <s:if test="kyzsec!=null">
				<input type="button" value="返回" onclick="javascript:location.href='kyzacct_findPageBean'" id="btn_back"
				onmouseover="this.style.backgroundPosition='left -40px'" onmouseout="this.style.backgroundPosition='left top'"/>
					
			</s:if>
		　　<s:else>
		        <input type="button" value="返回" onclick="javascript:location.href ='../kyzacct_findPageBean'" id="btn_back"
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
